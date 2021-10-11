package org.lmmarise.vue.persistent.org.pojo;

import lombok.*;
import org.lmmarise.vue.persistent.org.pojo.jpa.AuditModel;

import javax.persistence.*;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 2:23 下午
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String name;
    private String author;
    private Float price;
    @Transient
    private String description;
}
