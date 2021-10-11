package org.lmmarise.vue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.lmmarise.vue.persistent.org.pojo.Book;
import org.lmmarise.vue.web.WebAppTest;
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

import javax.annotation.Resource;

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
    void addBook() throws Exception {
        String content = objectMapper.writeValueAsString(Book.builder().id(1).name("《红楼梦》").author("曹雪芹").build());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
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
}