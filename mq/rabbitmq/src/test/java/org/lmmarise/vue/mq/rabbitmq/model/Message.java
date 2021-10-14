package org.lmmarise.vue.mq.rabbitmq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:18 上午
 */
@Setter
@Getter
@ToString
public class Message implements Serializable {
    private String content;
    private Date date;
}
