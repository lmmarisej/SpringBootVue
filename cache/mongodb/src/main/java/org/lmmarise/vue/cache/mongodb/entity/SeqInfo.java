package org.lmmarise.vue.cache.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 6:28 下午
 */
@Getter
@Setter
@Document(collection = "sequence")
public class SeqInfo {

    @Id
    private String id;

    @Field
    private String collName;

    @Field
    private Integer seqIdInteger;

    @Field
    private Long seqIdLong;
}
