package org.lmmarise.vue.persistent.dao.mybatis.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.domain.Book;

import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 10:28 下午
 */
@Mapper
public interface BookMapper1 {
    Integer addBook(Book book);

    Integer deleteBookById(Integer id);

    Integer updateBookById(Book book);

    Book getBookById(Integer id);

    List<Book> getAllBooks();
}
