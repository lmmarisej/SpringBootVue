package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "menuChildren",
            joinColumns = @JoinColumn(name = "children_menu_id"),   // referencedColumnName 默认为当前Menu表的 "id"
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Menu parentMenu;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "menuChildren")
    private Collection<Menu> childrenMenu = new HashSet<>();

    public Integer getParentMenuId() {
        return parentMenu != null ? parentMenu.getId() : null;
    }
}
