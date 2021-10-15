package org.lmmarise.vue.scheduled.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:59 下午
 */
@Component
public class MySchedule {

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() {
        System.out.println("==================" + Thread.currentThread() + "===============");
        System.out.println("fixedDelay:" + new Date());
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("==================" + Thread.currentThread() + "===============");
        System.out.println("fixedRate:" + new Date());
    }

    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void initialDelay() {
        System.out.println("initialDelay:" + new Date());
    }

    @Scheduled(cron = "0 * * * * ?")
    public void cron() {
        System.out.println("cron:" + new Date());
    }
}
