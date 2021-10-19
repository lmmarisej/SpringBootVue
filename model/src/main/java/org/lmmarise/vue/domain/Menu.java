package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends AuditModel {

    @Column(unique = true)
    private String name;
    private String path;
    private String url;
    private String component;
    private String iconCls;
    private Boolean enabled = true;

    @Embedded
    private Meta meta;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Collection<Role> roles = new HashSet<>();       // 中间表由 Role 来维护

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "menuChildren")
    private Collection<Menu> childrenMenu = new HashSet<>();
}
