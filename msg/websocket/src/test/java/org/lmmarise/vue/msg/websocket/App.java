package org.lmmarise.vue.msg.websocket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 系统测试
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 6:54 下午
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.run(args);
    }
}
