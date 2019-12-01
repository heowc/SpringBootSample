package com.example;

import com.example.domain.TestMessage;
import com.example.service.BasicService;
import com.example.web.BasicController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasicController.class)
public class MockMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BasicService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test() throws Exception {
        TestMessage message = new TestMessage("wonchul", 0);
        String result = mapper.writeValueAsString(message);

        given(service.jsonTest())
                .willReturn(message);

        mvc.perform(get("/jsonTest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }
}
