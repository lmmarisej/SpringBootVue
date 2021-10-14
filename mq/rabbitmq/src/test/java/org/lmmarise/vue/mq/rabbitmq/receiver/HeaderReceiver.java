package org.lmmarise.vue.mq.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:18 上午
 */
@Component
public class HeaderReceiver {

    @RabbitListener(queues = "name-queue")
    public void handler1(byte[] msg) {
        System.out.println("HeaderReceiver:name:" + new String(msg, 0, msg.length));
    }

    @RabbitListener(queues = "age-queue")
    public void handler2(byte[] msg) {
        System.out.println("HeaderReceiver:age:" + new String(msg, 0, msg.length));
    }
}
