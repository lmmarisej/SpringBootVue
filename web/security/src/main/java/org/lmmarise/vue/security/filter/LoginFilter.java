package org.lmmarise.vue.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/18 1:12 上午
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    private final SessionRegistry sessionRegistry;
    private final UserDetailsService userDetailsService;

    public LoginFilter(SessionRegistry sessionRegistry, UserDetailsService userDetailsService) {
        this.sessionRegistry = sessionRegistry;
        this.userDetailsService = userDetailsService;
    }

    ObjectMapper om = new ObjectMapper();

    /**
     * 试图登录的逻辑
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            // 异常抛出，将被登录失败回调给捕获 @see AbstractAuthenticationProcessingFilter.failureHandler
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String verify_code = (String) request.getSession().getAttribute("verify_code");

        String contentType = request.getContentType();
        if (contentType != null && (contentType.contains(MediaType.APPLICATION_JSON_VALUE) || contentType.contains(MediaType.APPLICATION_JSON_UTF8_VALUE))) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = om.readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                log.debug(e.getMessage());  // 用户传参错误，直接忽略
            } finally {
                String code = loginData.get("code");
                checkCode(request, code, verify_code);
            }
            // 通过用户名和密码作为认证凭证
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            sessionRegistry.registerNewSession(request.getSession(true).getId(), userDetails);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            checkCode(request, request.getParameter("code"), verify_code);
            return super.attemptAuthentication(request, response);
        }
    }

    /**
     * 校验登录用户输入的验证码
     */
    public void checkCode(HttpServletRequest req, String code, String verify_code) {
        if (verify_code == null || "".equals(code) || !verify_code.equalsIgnoreCase(code)) {
            req.setAttribute("errorMsg", "验证码有误");
            throw new AuthenticationServiceException("验证码有误");
        }
    }
}
