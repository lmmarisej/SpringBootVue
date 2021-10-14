package org.lmmarise.vue.security.model;

import lombok.Getter;
import lombok.Setter;
import org.lmmarise.vue.domain.AuditModel;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pattern;
    @OneToMany
    private List<Role> roles;
}
