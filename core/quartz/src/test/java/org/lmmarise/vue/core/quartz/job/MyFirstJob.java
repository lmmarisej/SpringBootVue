package org.lmmarise.vue.core.quartz.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 3:35 下午
 */
@Component
public class MyFirstJob {

    public void sayHello() {
        System.out.println("MyFirstJob:sayHello:" + new Date());
    }
}