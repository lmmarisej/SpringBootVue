package org.lmmarise.vue.mq.activemq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 10:01 下午
 */
@Setter
@Getter
@ToString
public class Message implements Serializable {
    private String content;
    private Date date;
}
