package org.lmmarise.vue.system.quartz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 1:25 下午
 */
@SpringBootApplication
public class App {

    /**
     * 可以直接运行，有了quartz，无需web环境让SpringBoot项目一直运行
     */
    public static void main(String... args) {
        new SpringApplicationBuilder(App.class).run();
    }
}
