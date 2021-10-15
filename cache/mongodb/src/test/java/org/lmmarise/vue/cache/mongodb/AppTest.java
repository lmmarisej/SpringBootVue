package org.lmmarise.vue.cache.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.cache.mongodb.repository.BookRepository;
import org.lmmarise.vue.domain.Book;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 7:10 下午
 */
@EntityScan("org.lmmarise.vue.persistent.org.domain")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongodbAutoConfiguration.class}, properties = "spring.main.allow-bean-definition-overroles_iding=true")
public class AppTest {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void crud() {
        List<Book> books = new ArrayList<>();

        Book b1 = new Book();
        b1.setName("朝花夕拾");
        b1.setAuthor("鲁迅");

        Book b2 = new Book();
        b2.setName("呐喊");
        b2.setAuthor("鲁迅");

        books.add(b1);
        books.add(b2);

        bookRepository.insert(books);
        List<Book> books1 = bookRepository.findByAuthorContains("鲁迅");
        List<Book> book = bookRepository.findByNameEquals("朝花夕拾");

        System.out.println(books1);
        System.out.println(book);
    }
}
