package org.lmmarise.vue.web.controller;

import org.lmmarise.vue.common.domain.PageQuery;
import org.lmmarise.vue.common.domain.Result;
import org.lmmarise.vue.persistent.org.domain.Book;
import org.lmmarise.vue.system.service.BookJpaService;
import org.lmmarise.vue.system.service.BookJdbcService;
import org.lmmarise.vue.system.service.BookMybatisService;
import org.lmmarise.vue.web.bind.annotation.ApiJsonRestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 6:45 下午
 */
@ApiJsonRestController
public class BookController {

    /*JDBC*/

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

    /*Mybatis*/

    @Resource
    private BookMybatisService bookMybatisService;

    @GetMapping("/booksMybatis")
    public Result getBooksMybatis() {
        return Result.ok(bookMybatisService.getAllBook());
    }

    @GetMapping("/booksMybatis1")
    public Result getBooksMybatis1() {
        return Result.ok(bookMybatisService.getAllBook1());
    }

    /*JPA*/

    @Resource
    private BookJpaService bookJpaService;

    @PostMapping("/bookJpa")
    public Result addBookJpa(@RequestBody Book book) {
        return Result.ok(bookJpaService.addBook(book));
    }

    @PostMapping("/findAllJpa")
    public Result findAllBookJpa(@RequestBody PageQuery page) {
        PageRequest pageable = PageRequest.of(page.getPage(), page.getSize());
        return Result.ok(bookJpaService.getBookByPage(pageable));
    }

    @PostMapping("/findAllJpa1")
    public Result findAllBookJpa1(@RequestBody PageQuery page) {
        PageRequest pageable = PageRequest.of(page.getPage(), page.getSize());
        return Result.ok(bookJpaService.getBookByPage1(pageable));
    }
}
