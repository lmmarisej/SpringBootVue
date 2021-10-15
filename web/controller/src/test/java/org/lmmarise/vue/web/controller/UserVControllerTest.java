package org.lmmarise.vue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.lmmarise.vue.web.WebAppTest;
import org.lmmarise.vue.web.model.User;
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
import java.util.Date;

/**
 * 测试验证器
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 4:25 下午
 */
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class UserVControllerTest extends WebAppTest {
    private static final Logger log = LoggerFactory.getLogger(UserVControllerTest.class);

    final ObjectMapper om = new ObjectMapper();

    @Resource
    public MockMvc mockMvc;

    @Resource
    private UserVController userVController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userVController).build();
    }

    @Test
    public void getUserV() throws Exception {
        User user = User
                .builder()
                .id(1).name("cxk11").address("上海").age(6).email("cxk@qq.com")
                .enabled(false).birthday(new Date())
                .build();
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/v/user")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(om.writeValueAsString(user))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
