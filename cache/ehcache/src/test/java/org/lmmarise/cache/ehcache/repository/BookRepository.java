package org.lmmarise.cache.ehcache.repository;

import org.lmmarise.cache.ehcache.generator.MyKeyGenerator;
import org.lmmarise.vue.domain.Book;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/14 1:11 上午
 */
@Service
// 当前类方法上的缓存操作，都作用于 "book_cache" 缓存池
// 数据结构 book_cache:Set[element1:Map<key, value>, element2, element3 ...]
@CacheConfig(cacheNames = "book_cache")
@Configurable
public class BookRepository {

    @Resource
    private MyKeyGenerator myKeyGenerator;

    // 本方法被调用前，会被 myKeyGenerator 拦截，先调用 MyKeyGenerator#generate 为当前方法的缓存生成 key
    // 每次执行前都根据 key 查找缓存进行返回
    @Cacheable(keyGenerator = "myKeyGenerator")
    public Book getBookById(Integer id) {
        System.out.println("getBookById");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }

    // 每次执行都不会查找缓存，而是直接写入
    @CachePut(key = "#book.id")     // 取book的id属性为key，方法返回值为value，进行缓存
    public Book updateBookById(Book book) {
        System.out.println("updateBookById");
        book.setName("三国演义2");
        return book;
    }

    // 默认方法执行之后删除缓存
    @CacheEvict(key = "#id")    // 根据名为id的参数删除缓存
    public void deleteBookById(Integer id) {
        System.out.println("deleteBookById");
    }

    /*Redis*/

    @Cacheable(value = "c1")        // 指定使用 c1 缓存策略，c1 也就是 redis     参见：RedisCacheConfig
    public String getBookById1(Integer id) {
        System.out.println("getBookById");
        return "这本书是三国演义";
    }

    @CachePut(value = "c1")
    public String updateBookById(Integer id) {
        return "这是全新的三国演义";
    }

    @CacheEvict(value = "c1")
    public void deleteById(Integer id) {
        System.out.println("deleteById");
    }

    @Cacheable(value = "c2")
    public String getBookById2(Integer id) {
        System.out.println("getBookById2");
        return "这本书是红楼梦";
    }
}
