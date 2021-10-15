package org.lmmarise.vue.system.quartz.job;

import lombok.Setter;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 3:34 下午
 */
@Setter
public class MySecondJob extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.out.println("hello:" + name + ":" + new Date());
    }
}
