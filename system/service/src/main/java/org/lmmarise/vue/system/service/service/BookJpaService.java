package org.lmmarise.vue.system.service.service;

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
    private BookJpaRepository bookJpaRepository;

    public Book addBook(Book book) {
        return bookJpaRepository.save(book);
    }

    public Book getBook(Book book) {
        return bookJpaRepository.getOne(book.getId());
    }

    public void updateBook(Book book) {
        bookJpaRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookJpaRepository.delete(book);
    }

    public List<Book> getAllBook() {
        return bookJpaRepository.findAll();
    }

    public Page<Book> getBookByPage(Pageable pageable) {
        return bookJpaRepository.findAll(pageable);
    }

    @Resource
    private BookJpaRepository1 bookJpaRepository1;

    public Page<Book> getBookByPage1(PageRequest pageable) {
        return bookJpaRepository1.findAll(pageable);
    }
}
