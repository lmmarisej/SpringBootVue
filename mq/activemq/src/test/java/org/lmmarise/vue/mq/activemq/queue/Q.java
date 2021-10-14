package org.lmmarise.vue.mq.activemq.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 10:01 下午
 */
@Component
public class Q {

    @Bean
    Queue queue() {
        return new ActiveMQQueue("amq");    // 接收端点
    }
}
