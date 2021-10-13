package org.lmmarise.vue.system.service.service;

import org.lmmarise.vue.domain.Book;
import org.lmmarise.vue.persistent.dao.mybatis.mapper.BookMapper;
import org.lmmarise.vue.persistent.dao.mybatis.mapper1.BookMapper1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 6:43 下午
 */
@Service
public class BookMybatisService {

    @Resource
    private BookMapper bookDao;

    @Resource
    private BookMapper1 bookDao1;

    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    public Book getBook(Book book) {
        return bookDao.getBookById(book.getId());
    }

    public Integer updateBook(Book book) {
        return bookDao.updateBookById(book);
    }

    public Integer deleteBook(Book book) {
        return bookDao.deleteBookById(book.getId());
    }

    public List<Book> getAllBook() {
        return bookDao.getAllBooks();
    }

    public List<Book> getAllBook1() {
        return bookDao1.getAllBooks();
    }
}
