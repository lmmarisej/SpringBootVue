package org.lmmarise.vue.msg.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置中间服务器
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 6:21 下午
 */
@Configuration
@EnableWebSocketMessageBroker       // 开启 WebSocket 的消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 消息代理前缀。以此开头的消息一律被转给消息代理 Broker
        config.enableSimpleBroker("/topic","/queue");
        // 过滤出需要被注解处理的消息。转给 @MessageMapping 处理，其它的转给 Broker 处理
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 配置 STOMP 协议
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 定义 endPoint 的前缀，开启sockjs支持，解决浏览器对 WebSocket 的兼容问题
        // 客户端通过 endPoint 配置的 URL 来建立 WebSocket 连接
        registry.addEndpoint("/chat").withSockJS();
    }
}
