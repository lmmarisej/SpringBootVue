package org.lmmarise.vue.security.service;

import org.lmmarise.vue.security.mapper.UserMapper;
import org.lmmarise.vue.security.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定义当持久化层是Mybatis时的用户查找逻辑
 */
@ConditionalOnBean(name = "sqlSessionFactoryBean")
@MapperScan(value = {"org.lmmarise.vue.security.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryBean")
@Service
public class MybatisUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }
}
