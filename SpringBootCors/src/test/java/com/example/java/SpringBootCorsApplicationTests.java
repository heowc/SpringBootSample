package com.example.java;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootCorsApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void allow_http_call() throws Exception {
        mvc.perform(get("/message/{value}", "spring"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void not_allow_http_call() throws Exception {
        mvc.perform(post("/message"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

}
