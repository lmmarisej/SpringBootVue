package org.lmmarise.vue.mq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 将消息队列绑定到一个 DirectExchange 上
 * <p>
 * 当消息到达 DirectExchange 时转发到与该条消息路由 key 相同的 Queue 上
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:19 上午
 */
@Configuration
public class RabbitDirectConfig {

    public final static String DIRECTNAME = "cxk-direct";

    @Bean
    Queue queue() {
        return new Queue("hello-queue");    // 创建消息队列
    }

    // 交换器用于将消息路由到指定消息队列
    @Bean
    DirectExchange directExchange() {
        // 在 rabbit mq 上创建 direct 交换器
        return new DirectExchange(DIRECTNAME, true, false);
    }

    @Bean
    Binding binding() {
        // 将队列和交换器绑定
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }
}
