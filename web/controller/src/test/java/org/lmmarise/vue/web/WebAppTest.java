package org.lmmarise.vue.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.system.ServiceAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 8:57 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebAutoConfiguration.class, ServiceAutoConfiguration.class}, properties = "application.yml")
public class WebAppTest {
    private static final Logger log = LoggerFactory.getLogger(WebAppTest.class);

    @Test
    public void contextLoads() {
        log.info("WebAppTest load OK!");
    }
}
