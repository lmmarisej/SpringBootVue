package org.lmmarise.vue.mq.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:16 上午
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "hello-queue")     // 消费指定队列的消息
    public void handler1(String msg) {
        System.out.println("DirectReceiver:" + msg);
    }
}
