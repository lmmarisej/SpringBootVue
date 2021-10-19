package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:44 下午
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hr extends AuditModel {
    private String name;
    private String phone;
    private String telephone;
    private String address;
    private Boolean enabled = true;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String userface;
    private String remark;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Collection<Role> roles;     // 中间表由 Hr 来维护

    @JsonIgnore
    @OneToMany(mappedBy = "hr", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<OpLog> opLogs;   // 中间表由 OpLog 来维护

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hr hr = (Hr) o;
        return Objects.equals(name, hr.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
