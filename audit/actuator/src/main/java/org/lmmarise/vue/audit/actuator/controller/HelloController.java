package org.lmmarise.vue.audit.actuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 5:51 下午
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello actuator!";
    }
}
