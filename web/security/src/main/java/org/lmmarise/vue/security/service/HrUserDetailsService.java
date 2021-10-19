package org.lmmarise.vue.security.service;

import org.lmmarise.vue.domain.Hr;
import org.lmmarise.vue.security.domain.HrUserDetails;
import org.lmmarise.vue.system.service.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 定义当持久化层是Mybatis时的用户查找逻辑
 */
@Service
public class HrUserDetailsService implements UserDetailsService {

    @Autowired
    private HrService hrService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrService.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        return new HrUserDetails(hr);
    }
}
