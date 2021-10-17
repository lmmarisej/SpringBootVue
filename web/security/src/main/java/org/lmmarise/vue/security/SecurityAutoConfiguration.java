package org.lmmarise.vue.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:04 下午
 */
@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = SecurityAutoConfiguration.class)
public class SecurityAutoConfiguration {
}
