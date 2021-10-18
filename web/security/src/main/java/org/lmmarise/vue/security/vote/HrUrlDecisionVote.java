package org.lmmarise.vue.security.vote;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 决策认证用户是否具备访问资源所需要的权限
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 2:58 下午
 */
@Component
public class HrUrlDecisionVote extends AuthenticatedVoter {

    /**
     * 根据被访问的URL所需权限进行投票
     * <p>
     * AccessDecisionVoter.ACCESS_GRANTED：赞成    1
     * AccessDecisionVoter.ACCESS_ABSTAIN：弃权    0
     * AccessDecisionVoter.ACCESS_DENIED：反对     -1
     * <p>
     * 注：直接在此`throw new AccessDeniedException("尚未登录");`抛出异常将越过权限管理器投票，直接一票否决。
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();   // 取得访问者提供的权限
        a:
        for (ConfigAttribute ca : attributes) {         // 访问URL需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    return AccessDecisionVoter.ACCESS_DENIED;
                }
                continue;
            }
            for (GrantedAuthority authority : auths) {
                if (ca.getAttribute().equals(authority.getAuthority())) {
                    continue a;
                }
            }
            return AccessDecisionVoter.ACCESS_DENIED;        // 投出反对票
        }
        return AccessDecisionVoter.ACCESS_GRANTED;
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
