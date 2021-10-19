package org.lmmarise.vue.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.lmmarise.vue.domain.Hr;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
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
@Getter
@Setter
public class HrUserDetails extends Hr implements UserDetails {

    @JsonIgnore
    private transient Hr hr;

    public HrUserDetails(Hr hr) {
        this.hr = hr;
        BeanUtils.copyProperties(hr, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return hr.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
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
