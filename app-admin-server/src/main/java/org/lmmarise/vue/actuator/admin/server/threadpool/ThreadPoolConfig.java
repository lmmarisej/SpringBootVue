package org.lmmarise.vue.actuator.admin.server.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:31 上午
 */
@Configuration
public class ThreadPoolConfig {

    @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}

