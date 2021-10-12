package org.lmmarise.vue.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 7:10 下午
 */
@EnableConfigurationProperties
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisAutoConfiguration.class})
public class AppTest {

    @Autowired
    private RedisTemplate<String, Map<String, String>> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTemplate() {
        ValueOperations<String, Map<String, String>> ops = redisTemplate.opsForValue();
        Map<String, String> map = new HashMap<>();
        map.put("name", "《鸡你太美》");
        map.put("author", "蔡徐坤");
        ops.set("cxk", map);
        System.out.println(ops.get("cxk"));
    }

    @Test
    public void stringRedisTemplate() {
        ValueOperations<String, String> ops2 = stringRedisTemplate.opsForValue();
        ops2.set("k1", "v1");
        System.out.println(ops2.get("k1"));
    }
}
