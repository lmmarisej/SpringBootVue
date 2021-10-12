package org.lmmarise.vue.cache.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 6:33 下午
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisConfig {
    private List<String> urls;
    private JedisPoolConfig poolConfig;

    @Bean
    RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        List<RedisNode> nodes = new ArrayList<>();
        for (String url : urls) {
            String[] strings = url.split(":");
            nodes.add(new RedisNode(strings[0], Integer.parseInt(strings[1])));
        }
//        configuration.setPassword(RedisPassword.of("lmmgo"));
        configuration.setClusterNodes(nodes);
        return configuration;
    }

    @Bean
    <K, V> RedisTemplate<K, V> redisTemplate() {
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }

    /**
     * Jedis 连接工厂
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration(), poolConfig);
    }
}
