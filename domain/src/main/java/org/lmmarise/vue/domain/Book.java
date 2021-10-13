package org.lmmarise.vue.domain;

import lombok.*;
import org.lmmarise.vue.cache.mongodb.annotation.AutoIncKey;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@Document(collection = "book")
public class Book extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MongoId(targetType = FieldType.INT32)
    @AutoIncKey
    public Integer id;
    @AutoIncKey
    public Long id1;
    private String name;
    private String author;
    private Float price;
    @Transient
    private String description;
}
