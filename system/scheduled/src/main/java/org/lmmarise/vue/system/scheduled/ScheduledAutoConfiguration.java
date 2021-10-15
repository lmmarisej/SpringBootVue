package org.lmmarise.vue.system.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:50 下午
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = ScheduledAutoConfiguration.class)
public class ScheduledAutoConfiguration {
}
