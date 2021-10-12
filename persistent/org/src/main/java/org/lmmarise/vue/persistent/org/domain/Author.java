package org.lmmarise.vue.persistent.org.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 2:24 下午
 */
@Getter
@Setter
@ToString
public class Author {
    private Integer id;
    private String name;
    private Integer age;
}
