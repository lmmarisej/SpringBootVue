package org.lmmarise.vue.persistent.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.persistent.org.pojo.Book;

import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 10:28 下午
 */
@Mapper
public interface BookMapper {
    Integer addBook(Book book);

    Integer deleteBookById(Integer id);

    Integer updateBookById(Book book);

    Book getBookById(Integer id);

    List<Book> getAllBooks();
}
