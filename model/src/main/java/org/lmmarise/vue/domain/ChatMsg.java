package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:34 下午
 */
@Getter
@Setter
public class ChatMsg {
    private Integer id;
    private String from;
    private String to;
    private String content;
    private Date date;
    private String fromNickname;
}
