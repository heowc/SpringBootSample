package com.tistory.heowc;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootExceptionApplicationTests {

    public static final String PREFIX_SPRING_BOOT_WHITE_LABEL = "<html><body><h1>Whitelabel Error Page</h1>";

    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @CsvSource({
            "/, 200, Basic Error",
            "/extension, 200, extension error",
            "/extension4, 400, restful error",
            "/extension5, 200, ",
            "/extension6, 200, 500 INTERNAL_SERVER_ERROR \"Server Error\""
    })
    void test_error(String url, Integer statusCode, String message) throws Exception {
        mvc.perform(get(url))
                .andExpect(status().is(statusCode))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message", is(message)))
                .andDo(print())
                .andReturn();
    }

    @Test
    void test_extension2() throws Exception {
        mvc.perform(get("/extension2"))
                .andExpect(status().is(400))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8"))
                .andExpect(content().string(startsWith(PREFIX_SPRING_BOOT_WHITE_LABEL)))
                .andDo(print())
                .andReturn();
    }

    @Test
    void test_extension3() throws Exception {
        mvc.perform(get("/extension2"))
                .andExpect(status().is(400))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8"))
                .andExpect(content().string(startsWith(PREFIX_SPRING_BOOT_WHITE_LABEL)))
                .andDo(print())
                .andReturn();
    }
}
