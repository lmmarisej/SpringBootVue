package org.lmmarise.vue.persistent.org.domain;

import lombok.*;

import javax.persistence.*;

/**
 * -- auto-generated definition
 * create table book
 * (
 *     id                 int auto_increment
 *         primary key,
 *     create_by          varchar(255) null,
 *     create_time        datetime     null,
 *     last_modified_by   varchar(255) null,
 *     last_modified_time datetime     null,
 *     author             varchar(255) null,
 *     name               varchar(255) null,
 *     price              float        null
 * );
 *
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
