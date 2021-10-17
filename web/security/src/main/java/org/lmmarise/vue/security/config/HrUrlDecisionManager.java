package org.lmmarise.vue.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 2:58 下午
 */
@Component
public class HrUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> cas) {
        Collection<? extends GrantedAuthority> auths = auth.getAuthorities();   // 取得访问者提供的权限
        a:for (ConfigAttribute ca : cas) {  // 访问URL需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录");
                }
                continue;
            }
            for (GrantedAuthority authority : auths) {
                if (ca.getAttribute().equals(authority.getAuthority())) {
                    continue a;
                }
            }
            throw new AccessDeniedException("权限不足");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
