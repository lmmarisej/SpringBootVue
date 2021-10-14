package org.lmmarise.vue.msg.websocket.controller;

import org.lmmarise.vue.msg.websocket.model.Chat;
import org.lmmarise.vue.msg.websocket.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 6:23 下午
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 当前方法处理完消息后，转发给 /topic/greetings，/topic 开头又会被 Broker 拦截进行广播
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")     // 用户点击连接，就连入 /topic/greetings，相当于观察者模式
    public Message greeting(Message message) {
        return message;
    }

    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat) {
        String from = principal.getName();
        chat.setFrom(from);
        messagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
