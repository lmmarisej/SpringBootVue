package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:23 下午
 */
public class UrgencyMsg extends AbMsg {

    public UrgencyMsg(IMsg msg) {
        super(msg);
    }

    void sendMsg(String msg, String toUser) {
        // 将消息与发送器连接--桥接
        super.sendMsg(msg, toUser);
    }
}
