package org.lmmarise.vue.msg.websocket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chat {
    private String to;
    private String from;
    private String content;
}
