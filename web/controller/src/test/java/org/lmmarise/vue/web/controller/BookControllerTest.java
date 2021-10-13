package org.lmmarise.vue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.lmmarise.vue.common.domain.PageQuery;
import org.lmmarise.vue.domain.Book;
import org.lmmarise.vue.web.WebAppTest;
import org.lmmarise.vue.web.controller.controller.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 7:00 下午
 */
@AutoConfigureMockMvc
@EnableAutoConfiguration
class BookControllerTest extends WebAppTest {
    private static final Logger log = LoggerFactory.getLogger(BookControllerTest.class);

    @Resource
    public MockMvc mockMvc;

    @Resource
    private BookController bookController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getBook() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/book")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(Book.builder().id(1).build()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllBooks() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Tag("exception-testing-SQLIntegrityConstraintViolationException")
    void addBook() throws Exception {
        String content = objectMapper.writeValueAsString(Book.builder().id(1).name("《红楼梦》").author("曹雪芹").build()); // 不支持自增，多次插入必定异常
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/book")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(content))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (SQLIntegrityConstraintViolationException | NestedServletException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("未出现期望异常:" + e.getMessage());
        }
    }

    @Test
    void updateBook() throws Exception {
        String content = objectMapper.writeValueAsString(Book.builder().id(1).name("《红楼梦》").author("曹雪芹").build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void deleteBook() throws Exception {
        String content = objectMapper.writeValueAsString(Book.builder().id(1).build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    /*Mybatis*/

    @Test
    void getAllBooksMybatis() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/booksMybatis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllBooksMybatis1() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/booksMybatis1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    /*JPA*/

    @Test
    void addBookJpa() throws Exception {
        String content = objectMapper.writeValueAsString(Book.builder().name("《红楼梦》").author("曹雪芹").build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/bookJpa")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void findAllBookJpa() throws Exception {
        String content = objectMapper.writeValueAsString(PageQuery.builder().page(0).size(2).build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/findAllJpa")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    // 测试 JPA 使用第二个数据库数据源
    @Test
    void findAllBookJpa1() throws Exception {
        String content = objectMapper.writeValueAsString(PageQuery.builder().page(0).size(2).build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/findAllJpa1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}