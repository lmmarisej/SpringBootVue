package org.lmmarise.vue.cache.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.cache.mongodb.repository.BookRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 7:10 下午
 */
@EntityScan("org.lmmarise.vue.persistent.org.domain")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongodbAutoConfiguration.class}, properties = "spring.main.allow-bean-definition-overriding=true")
public class AppTest {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
    }
}
