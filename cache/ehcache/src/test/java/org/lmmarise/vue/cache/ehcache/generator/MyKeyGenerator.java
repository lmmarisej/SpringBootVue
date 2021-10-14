package org.lmmarise.vue.cache.ehcache.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:12 上午
 */
@Component
public class MyKeyGenerator implements KeyGenerator {

    /**
     * 为缓存生成 key
     *
     * @param target 使用 @Cacheable(keyGenerator = "myKeyGenerator") 的当前调用方法所在的实例对象。
     * @param method 被 @Cacheable(keyGenerator = "myKeyGenerator") 标注的当前调用方法。
     * @param params 方法参数。
     * @return 生成的缓存 key
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return Arrays.toString(params);
    }
}
