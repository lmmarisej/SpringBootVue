package org.lmmarise.vue.security.model;

import lombok.Getter;
import lombok.Setter;
import org.lmmarise.vue.domain.AuditModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Role extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nameZh;
}
