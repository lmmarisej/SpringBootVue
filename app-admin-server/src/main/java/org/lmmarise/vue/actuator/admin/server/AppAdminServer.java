package org.lmmarise.vue.actuator.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:24 上午
 */
@SpringBootApplication
@EnableAdminServer
public class AppAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(AppAdminServer.class, args);
    }
}
