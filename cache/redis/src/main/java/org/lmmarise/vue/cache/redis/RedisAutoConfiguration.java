package org.lmmarise.vue.cache.redis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 6:13 下午
 */
@Configuration
@ComponentScan(basePackageClasses = RedisAutoConfiguration.class)
public class RedisAutoConfiguration {
}
