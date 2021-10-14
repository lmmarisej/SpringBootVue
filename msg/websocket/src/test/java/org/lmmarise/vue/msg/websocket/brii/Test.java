package org.lmmarise.vue.msg.websocket.brii;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 8:21 下午
 */
public class Test {

    public static void main(String[] args) {
        IMsg msg = new SmsMsg();    // 定义发送方式

        AbMsg abMsg = new NormalMsg(msg);

        abMsg.sendMsg("鸡你太美", "cxk");   // 发送

        msg = new EmailMsg();

        abMsg = new UrgencyMsg(msg);

        abMsg.sendMsg("大碗宽面", "w1f");
    }
}
