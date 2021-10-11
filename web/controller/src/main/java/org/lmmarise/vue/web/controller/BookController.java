package org.lmmarise.vue.web.controller;

import org.lmmarise.vue.persistent.org.pojo.Book;
import org.lmmarise.vue.system.service.BookJpaService;
import org.lmmarise.vue.system.service.BookJdbcService;
import org.lmmarise.vue.web.bind.annotation.ApiJsonRestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 6:45 下午
 */
@ApiJsonRestController
public class BookController {

    @Resource
    private BookJdbcService bookJdbcService;

    @GetMapping("/book")
    public Book getBook(@RequestBody Book book) {
        return bookJdbcService.getBook(book);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookJdbcService.getAllBook();
    }

    @PostMapping("/book")
    public Integer addBook(@RequestBody Book book) {
        return bookJdbcService.addBook(book);
    }

    @PutMapping("/book")
    public Integer updateBook(@RequestBody Book book) {
        return bookJdbcService.updateBook(book);
    }

    @DeleteMapping("/book")
    public Integer deleteBook(@RequestBody Book book) {
        return bookJdbcService.deleteBook(book);
    }

    @Resource
    private BookJpaService bookJpaService;

    @PostMapping("/bookJpa")
    public Book addBookJpa(@RequestBody Book book) {
        return bookJpaService.addBook(book);
    }
}
