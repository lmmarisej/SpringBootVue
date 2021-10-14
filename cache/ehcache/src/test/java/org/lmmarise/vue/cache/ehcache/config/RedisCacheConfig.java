package org.lmmarise.vue.cache.ehcache.config;

import org.lmmarise.vue.cache.redis.config.RedisConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 2:08 上午
 */
@ConditionalOnProperty(prefix = "cache", name = "redis", havingValue = "true")
@Configuration
@ComponentScan(basePackageClasses = RedisConfig.class)
public class RedisCacheConfig {

    @Resource
    private RedisConnectionFactory conFactory;

    @Bean
    public RedisCacheManager redisCacheManager() {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig().prefixKeysWith("cxk:")
                .disableCachingNullValues().entryTtl(Duration.ofMinutes(30));
        configMap.put("c1", redisCacheConfig);
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(conFactory);
        return new RedisCacheManager(cacheWriter, RedisCacheConfiguration.defaultCacheConfig(), configMap);
    }
}
