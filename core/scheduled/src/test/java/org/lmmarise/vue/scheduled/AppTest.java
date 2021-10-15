package org.lmmarise.vue.scheduled;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 12:52 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
@ContextConfiguration
public class AppTest {

    @Test
    public void run() {
        App.main();
    }
}
