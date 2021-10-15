package org.lmmarise.vue.audit.actuator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 5:43 下午
 */
@Configuration
@ComponentScan(basePackageClasses = ActuatorAutoConfiguration.class)
public class ActuatorAutoConfiguration {
}
