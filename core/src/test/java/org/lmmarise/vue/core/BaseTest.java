package org.lmmarise.vue.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 9:04 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BaseTest.class)
public class BaseTest {

    @Test
    public void context() {
    }
}
