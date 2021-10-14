package org.lmmarise.vue.mq.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.mq.activemq.jms.JmsComponent;
import org.lmmarise.vue.mq.activemq.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 9:59 下午
 */
@SpringBootTest(classes = TestApp.class)
@SpringBootConfiguration
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestApp {

    @Test
    public void run() {
    }

    @Autowired
    private JmsComponent jmsComponent;

    @Test
    public void contextLoads() {
        Message msg = new Message();
        msg.setContent("hello jms!");
        msg.setDate(new Date());
        jmsComponent.send(msg);
    }
}
