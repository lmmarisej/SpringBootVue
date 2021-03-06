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

/**
 * ?????????????????????URL????????????????????????????????????
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:10 ??????
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
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  // ????????????????????????????????????
    }

    /**
     * ?????? parent:AuthenticationManager ?????????????????? http:HttpSecurity ?????? AuthenticationProvider ??????????????????????????? parent ?????????
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // ??????????????? UserDetails ??? Service ?????? AuthenticationManager ?????????
        // ??????????????? LoginFilter ???????????? AuthenticationManager#authenticate ????????????????????????????????????????????? UsernamePasswordAuthenticationToken
        // ????????? AuthenticationManager ???????????????????????? username ??? hrUserDetailsService ????????????????????? ????????? ??? ?????????
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
     * ???????????????????????????
     *
     * @see AffirmativeBased  ????????????
     * @see UnanimousBased  ????????????
     * @see ConsensusBased  ??????????????????
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // ???????????????????????????
        List<AccessDecisionVoter<?>> decisionVoters =
                Arrays.asList(
                        new WebExpressionVoter(),                   // ??????URL????????????
                        new RoleVoter(),                            // ???????????????????????????
                        new RoleHierarchyVoter(roleHierarchy()),    // ????????????
                        new Jsr250Voter(),                          // @PermitAll???@DenyAll
                        new AuthenticatedVoter(),                   // ??????????????????????????? ConfigAttribute.getAttribute()
                        new HrUrlDecisionVote(),                    // ?????????
                        new AnonymousVoter()                        // ???????????????
                );
        return new AffirmativeBased(decisionVoters);
    }

    /**
     * ????????????
     */
    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(sessionRegistry(), hrUserDetailsService);
        // ??????????????????
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Result ok = Result.ok("????????????", userDetails);
            String body = new ObjectMapper().writeValueAsString(ok);
            out.write(body);
            out.flush();
            out.close();
        });
        // ??????????????????
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            Result respFail = Result.fail(Result.Code.FAIL, exception.getMessage());
            if (request.getAttribute("errorMsg") != null) {
                respFail.setMsg(request.getAttribute("errorMsg").toString());
            } else if (exception instanceof LockedException) {
                respFail.setMsg("???????????????");
            } else if (exception instanceof CredentialsExpiredException) {
                respFail.setMsg("????????????");
            } else if (exception instanceof AccountExpiredException) {
                respFail.setMsg("????????????");
            } else if (exception instanceof DisabledException) {
                respFail.setMsg("???????????????");
            } else if (exception instanceof BadCredentialsException) {
                respFail.setMsg("???????????????????????????");
            }
            out.write(new ObjectMapper().writeValueAsString(respFail));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());  // ???????????????
        loginFilter.setFilterProcessesUrl("/doLogin");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy =
                new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());  // ???????????? session ????????????
        sessionStrategy.setMaximumSessions(1);  // ???????????????????????? Session?????? session ??????????????? 1
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);  // ??????????????????????????????????????????
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(hrFilterInvocationSecurityMetadataSource); // ??????????????????????????????
                        object.setAccessDecisionManager(accessDecisionManager);  // ??????????????????
                        return object;
                    }
                })
                .and()
                .logout()
                // ??????????????????
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.ok("????????????")));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                // ?????????????????????????????????????????????????????????
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.setStatus(401);
                    PrintWriter out = resp.getWriter();
                    Result result = Result.fail(Result.Code.UN_AUTHENTICATION, "????????????");
                    if (authException instanceof InsufficientAuthenticationException) {
                        result.setMsg("????????????????????????");
                    }
                    out.write(new ObjectMapper().writeValueAsString(result));
                    out.flush();
                    out.close();
                });
        // ??????????????????
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(Result.fail(Result.Code.UN_AUTHENTICATION, "????????????????????????????????????????????????")));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        // ???????????????????????????????????????????????????????????????????????????
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @SuppressWarnings("all")
    public static String[] getStaticResources() {
        if (staticResources == null) return new String[]{};
        return staticResources.replaceAll(" ", "").split(",");
    }
}
