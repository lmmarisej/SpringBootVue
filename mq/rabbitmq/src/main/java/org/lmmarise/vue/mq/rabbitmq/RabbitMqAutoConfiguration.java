package org.lmmarise.vue.mq.rabbitmq;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 11:27 下午
 */
@Configuration
@ComponentScan(basePackageClasses = RabbitMqAutoConfiguration.class)
public class RabbitMqAutoConfiguration {
}
