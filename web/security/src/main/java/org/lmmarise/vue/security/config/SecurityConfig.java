package org.lmmarise.vue.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lmmarise.vue.common.domain.Result;
import org.lmmarise.vue.security.filter.LoginFilter;
import org.lmmarise.vue.security.vote.AnonymousVoter;
import org.lmmarise.vue.security.vote.HrUrlDecisionVote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义需要认证的URL，以及组合认证授权等功能
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:10 下午
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String staticResources = "/css/**,/js/**,/index.html,/img/**,/fonts/**,/favicon.ico,/verifyCode";

    @Resource
    private FilterInvocationSecurityMetadataSource hrFilterInvocationSecurityMetadataSource;

    @Resource
    private AccessDecisionManager accessDecisionManager;

    @Resource
    private UserDetailsService hrUserDetailsService;

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  // 支持根据前缀判断加密方式
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 将查询用户 UserDetails 的 Service 交给 AuthenticationManager 去使用
        // 在自定义的 LoginFilter 中，通过 AuthenticationManager#authenticate 将用户输入的用户名和密码封装为 UsernamePasswordAuthenticationToken
        // 最后由 AuthenticationManager 从根据用户输入的 username 从 hrUserDetailsService 查出用户，比对 用户名 和 密码。
        auth.userDetailsService(hrUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(getStaticResources());
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 访问资格决策管理器
     *
     * @see AffirmativeBased  一票通过
     * @see UnanimousBased  一票否决
     * @see ConsensusBased  少数服从多数
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // 硬编码，避免被攻击
        List<AccessDecisionVoter<?>> decisionVoters =
                Arrays.asList(
                        new WebExpressionVoter(),                   // 基于URL地址权限
                        new RoleVoter(),                            // 基于登录主体的角色
                        new RoleHierarchyVoter(roleHierarchy()),    // 层次权限
                        new Jsr250Voter(),                          // @PermitAll、@DenyAll
                        new AuthenticatedVoter(),                   // 被访问资源所需权限 ConfigAttribute.getAttribute()
                        new HrUrlDecisionVote(),                    // 自定义
                        new AnonymousVoter()                        // 可匿名访问
                );
        return new AffirmativeBased(decisionVoters);
    }

    /**
     * 登录逻辑
     */
    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(sessionRegistry(), hrUserDetailsService);
        // 登录成功回调
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Result ok = Result.ok("登录成功", userDetails);
            String body = new ObjectMapper().writeValueAsString(ok);
            out.write(body);
            out.flush();
            out.close();
        });
        // 登录失败回调
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            Result respFail = Result.fail(Result.Code.FAIL, "登录失败");
            if (request.getAttribute("errorMsg") != null) {
                respFail.setMsg(exception.getMessage());
            } else if (exception instanceof LockedException) {
                respFail.setMsg("账户被锁定");
            } else if (exception instanceof CredentialsExpiredException) {
                respFail.setMsg("密码过期");
            } else if (exception instanceof AccountExpiredException) {
                respFail.setMsg("账户过期");
            } else if (exception instanceof DisabledException) {
                respFail.setMsg("账户被禁用");
            } else if (exception instanceof BadCredentialsException) {
                respFail.setMsg("用户名或者密码错误");
            }
            out.write(new ObjectMapper().writeValueAsString(respFail));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());  // 身份验证器
        loginFilter.setFilterProcessesUrl("/doLogin");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy =
                new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());  // 登录用户 session 统一存储
        sessionStrategy.setMaximumSessions(1);  // 登录时检查已登录 Session，将 session 并发限制在 1
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);  // 作为本次登录会话控制认证策略
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(hrFilterInvocationSecurityMetadataSource); // 访问资源所需权限提供
                        object.setAccessDecisionManager(accessDecisionManager);  // 访问资格鉴权
                        return object;
                    }
                })
                .and()
                .logout()
                // 登出成功回调
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.ok("登出成功")));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                // 没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.setStatus(401);
                    PrintWriter out = resp.getWriter();
                    Result result = Result.fail(Result.Code.UN_AUTHENTICATION, "访问失败");
                    if (authException instanceof InsufficientAuthenticationException) {
                        result.setMsg("验证身份权限不足");
                    }
                    out.write(new ObjectMapper().writeValueAsString(result));
                    out.flush();
                    out.close();
                });
        // 并发会话管理
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(Result.fail(Result.Code.UN_AUTHENTICATION, "您已在另一台设备登录，请重新登录")));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        // 认证逻辑安插在过滤器链中用户名和密码认证同一个位置
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @SuppressWarnings("all")
    public static String[] getStaticResources() {
        if (staticResources == null) return new String[]{};
        return staticResources.replaceAll(" ", "").split(",");
    }
}
