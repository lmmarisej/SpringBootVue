package org.lmmarise.vue.log;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 7:14 下午
 */
@Configuration
@ComponentScan(basePackageClasses = {LogAutoConfiguration.class})
public class LogAutoConfiguration {
}
