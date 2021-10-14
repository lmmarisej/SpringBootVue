package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:21 下午
 */
public abstract class AbMsg {
    private IMsg msg;

    public AbMsg(IMsg msg) {
        this.msg = msg;
    }

    void sendMsg(String msg, String toUser) {
        this.msg.send(msg, toUser);
    }
}
