package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:27 下午
 */
public class SmsMsg implements IMsg {

    @Override
    public void send(String msg, String toUser) {
        System.out.println("短信：" + msg + " => " + toUser);
    }
}
