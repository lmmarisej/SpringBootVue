package org.lmmarise.vue.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.lmmarise.vue.web.controller.controller.UserController;
import org.lmmarise.vue.web.WebAppTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 9:19 下午
 */
@PropertySource("classpath:application.yml")
@AutoConfigureMockMvc
public class UserControllerTest extends WebAppTest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void demo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }
}
