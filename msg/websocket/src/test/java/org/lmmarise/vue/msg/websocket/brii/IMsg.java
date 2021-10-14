package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:21 下午
 */
public interface IMsg {
    void send(String msg, String toUser);
}
