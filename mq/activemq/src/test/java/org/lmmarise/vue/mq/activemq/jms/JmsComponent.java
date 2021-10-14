package org.lmmarise.vue.mq.activemq.jms;

import org.lmmarise.vue.mq.activemq.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 10:02 下午
 */
@Component
public class JmsComponent {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @Autowired
    private Queue queue;

    public void send(Message msg) {
        messagingTemplate.convertAndSend(this.queue, msg);
    }

    @JmsListener(destination = "amq")
    public void receive(Message msg) {
        System.out.println("Current Thread:" + Thread.currentThread());
        System.out.println("receive:" + msg);
    }
}
