package org.lmmarise.vue.cache.mongodb.repository;

import org.lmmarise.vue.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 12:57 下午
 */
public interface BookRepository extends MongoRepository<Book, Integer> {

    List<Book> findByAuthorContains(String author);

    List<Book> findByNameEquals(String name);
}
