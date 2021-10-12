package org.lmmarise.vue.persistent.dao.jdbc.dao;

import org.lmmarise.vue.persistent.org.domain.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 6:29 下午
 */
@Repository
public class BookJdbcDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO book(id, name, author) VALUES (?, ?, ?)", book.getId(), book.getName(), book.getAuthor());
    }

    public int updateBookById(Book book) {
        return jdbcTemplate.update("UPDATE book SET name=?, author=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getId());
    }

    public int deleteBookById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Book getBookById(Integer id) {
        return jdbcTemplate.queryForObject("select * from book where id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }
}
