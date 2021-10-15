package org.lmmarise.vue.scheduled;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 1:25 下午
 */
@SpringBootApplication
public class App {

    public static void main(String... args) {
        new SpringApplicationBuilder(App.class).run();
    }
}
