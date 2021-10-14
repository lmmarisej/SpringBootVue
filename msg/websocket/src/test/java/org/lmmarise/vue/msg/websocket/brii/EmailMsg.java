package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:30 下午
 */
public class EmailMsg implements IMsg {
    @Override
    public void send(String msg, String toUser) {
        System.out.println("邮件发送：" + msg + " => " + toUser);
    }
}
