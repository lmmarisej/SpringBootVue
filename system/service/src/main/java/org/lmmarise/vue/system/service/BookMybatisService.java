package org.lmmarise.vue.system.service;

import org.lmmarise.vue.persistent.dao.mapper.BookMapper;
import org.lmmarise.vue.persistent.org.pojo.Book;
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
}
