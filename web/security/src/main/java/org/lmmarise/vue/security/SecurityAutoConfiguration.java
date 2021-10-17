package org.lmmarise.vue.security;

import org.lmmarise.vue.domain.Menu;
import org.lmmarise.vue.domain.Role;
import org.lmmarise.vue.persistent.dao.mybatis.mapper.MenuMapper;
import org.lmmarise.vue.security.service.MybatisUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:04 下午
 */
@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = SecurityAutoConfiguration.class)
public class SecurityAutoConfiguration {

    /**
     * 持久化配置
     */
    @ConditionalOnBean(MybatisUserDetailsService.class)
    @Component
    static class MybatisFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

        @Autowired
        private MenuMapper menuMapper;

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        @Override
        public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
            String requestUrl = ((FilterInvocation) object).getRequestUrl();
            List<Menu> allMenus = menuMapper.getAllMenus();
            for (Menu menu : allMenus) {
                if (antPathMatcher.match(menu.getPath(), requestUrl)) {
                    List<Role> roles = menu.getRoles();
                    String[] roleArr = new String[roles.size()];
                    for (int i = 0; i < roleArr.length; i++) {
                        roleArr[i] = roles.get(i).getName();
                    }
                    return SecurityConfig.createList(roleArr);
                }
            }
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        @Override
        public Collection<ConfigAttribute> getAllConfigAttributes() {
            return null;
        }

        @Override
        public boolean supports(Class<?> clazz) {
            return FilterInvocation.class.isAssignableFrom(clazz);
        }
    }
}
