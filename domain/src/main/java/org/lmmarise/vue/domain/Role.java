package org.lmmarise.vue.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String nameZh;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Hr> hrs;         // 中间表由 Hr 来维护

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Menu> menus;     // 中间表由 Role 来维护
}
