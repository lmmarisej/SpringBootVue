package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Role> roles = new ArrayList<>();       // 中间表由 Role 来维护

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Menu parentMenu;

    @JsonIgnore
    @OneToMany(mappedBy = "parentMenu", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Menu> childrenMenu = new ArrayList<>();
}
