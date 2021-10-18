package org.lmmarise.vue.security.vote;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * 对匿名访问鉴权
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/19 12:10 上午
 */
public class AnonymousVoter implements AccessDecisionVoter<Object> {

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        return 0;
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
