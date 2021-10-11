package org.lmmarise.vue;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 项目启动入口
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 1:31 下午
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}
