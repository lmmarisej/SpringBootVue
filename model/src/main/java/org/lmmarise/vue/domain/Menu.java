package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    private Collection<Role> roles = new ArrayList<>();       // 中间表由 Role 来维护

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "parentMenuId")
    private Menu parentMenu;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Collection<Menu> childrenMenu = new ArrayList<>();

    @Transient
    private Integer parentMenuId;

    @Transient
    private Collection<Integer> childrenMenuIds;

    public Integer getParentMenuId() {
        return parentMenu != null ? parentMenu.getId() : null;
    }

    public List<Integer> getChildrenMenuIds() {
        return childrenMenu != null
                ? childrenMenu.stream().map(AuditModel::getId).collect(Collectors.toList())
                : null;
    }
}
