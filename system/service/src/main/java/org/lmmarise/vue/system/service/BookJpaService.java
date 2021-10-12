package org.lmmarise.vue.system.service;

import org.lmmarise.vue.persistent.dao.jpa.repository.BookJpaRepository;
import org.lmmarise.vue.persistent.dao.jpa.repository1.BookJpaRepository1;
import org.lmmarise.vue.persistent.org.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 6:43 下午
 */
@Service
public class BookJpaService {

    @Resource
    private BookJpaRepository bookDao;

    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    public Book getBook(Book book) {
        return bookDao.getOne(book.getId());
    }

    public void updateBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public List<Book> getAllBook() {
        return bookDao.findAll();
    }

    public Page<Book> getBookByPage(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    @Resource
    private BookJpaRepository1 bookDao1;

    public Page<Book> getBookByPage1(PageRequest pageable) {
        return bookDao1.findAll(pageable);
    }
}
