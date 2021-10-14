package org.lmmarise.vue.cache.ehcache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.cache.ehcache.repository.BookRepository;
import org.lmmarise.vue.domain.Book;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:09 上午
 */
@EnableCaching
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableAutoConfiguration
@ComponentScan("org.lmmarise.vue.cache.ehcache.*")
public class TestApp {

    @Resource
    private BookRepository bookRepository;

    @Test
    public void run() {
    }

    @Test
    public void testEhcacheCache() {
        bookRepository.getBookById(1);
        bookRepository.getBookById(1);
        bookRepository.deleteBookById(1);
        Book b3 = bookRepository.getBookById(1);

        System.out.println("b3:" + b3);

        Book b = new Book();
        b.setName("三国演义");
        b.setAuthor("罗贯中");
        b.setId(1);

        bookRepository.updateBookById(b);

        Book b4 = bookRepository.getBookById(1);

        System.out.println("b4:" + b4);
    }

    @Test
    public void testRedisCache() {
        bookRepository.getBookById1(1);
        bookRepository.updateBookById(1);
        bookRepository.deleteById(1);
        String b3 = bookRepository.getBookById2(1);
        System.out.println("b3:" + b3);
    }
}
