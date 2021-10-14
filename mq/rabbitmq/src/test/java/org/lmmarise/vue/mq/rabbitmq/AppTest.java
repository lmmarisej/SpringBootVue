package org.lmmarise.vue.mq.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.mq.rabbitmq.config.RabbitFanoutConfig;
import org.lmmarise.vue.mq.rabbitmq.config.RabbitHeaderConfig;
import org.lmmarise.vue.mq.rabbitmq.config.RabbitTopicConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JMS 从 API 层面对消息中间件进行统一，AMQP 从协议层面统一
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 11:27 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest     // 自动搜索 @SpringBootConfiguration
@SpringBootConfiguration
@EnableAutoConfiguration
public class AppTest {

    @Test
    public void run() {
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void directTest() {
        // 消息需要携带路由键，以被交换机送达与指定的路由键相同的消息队列
        rabbitTemplate.convertAndSend("hello-queue", "你好 直接！");
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME, null, "hello fanout!");
    }

    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.news", "小米新闻。。。");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.news", "华为新闻。。。");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "phone.news", "手机新闻。。。");
    }

    @Test
    public void headerTest() {
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue".getBytes())
                .setHeader("name", "cxk").build();
        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue".getBytes())
                .setHeader("age", "99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, nameMsg);
    }
}
