package org.lmmarise.vue.audit.actuator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 5:43 下午
 */
@EnableScheduling
@Configuration
@ComponentScan(basePackageClasses = ActuatorAutoConfiguration.class)
public class ActuatorAutoConfiguration {
}
