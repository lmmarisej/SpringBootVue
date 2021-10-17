package org.lmmarise.vue.security.domain;

import org.lmmarise.vue.domain.Hr;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 封装 Hr 的 UserDetails，以支持 SpringSecurity
 * <p>
 * UserDetails 在 SpringSecurity 中支持与 Principal 转换
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/18 12:48 上午
 */
public class HrUserDetails extends Hr implements UserDetails {

    private final Hr hr;

    public HrUserDetails(Hr hr) {
        this.hr = hr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return hr.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return hr.getPassword();
    }

    @Override
    public String getUsername() {
        return hr.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
