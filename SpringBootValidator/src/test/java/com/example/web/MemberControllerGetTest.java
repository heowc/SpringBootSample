package com.example.web;

import com.example.domain.Member;
import com.example.errorhandler.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerGetTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_success() throws Exception {
        final Member member = new Member("heowc", 14, "01012345678");

        assertResponse(member, status().isOk(), toJsonString(member));
    }

    @Test
    void test_InvalidByName() throws Exception {
        final Member member = new Member(null, 14, "01012345678");
        final String errorMessageToJson = toJsonString(
                Collections.singletonList(ErrorMessage.of("name", "널이어서는 안됩니다"))
        );

        assertResponse(member, status().isBadRequest(), errorMessageToJson);
    }

    @Test
    void test_InvalidByAge() throws Exception {
        final Member member = new Member("heowc", 13, "01012345678");
        final String errorMessageToJson = toJsonString(
                Collections.singletonList(ErrorMessage.of("age", "14 이상이어야 합니다"))
        );

        assertResponse(member, status().isBadRequest(), errorMessageToJson);
    }

    @Test
    void test_InvalidByPhone() throws Exception {
        final Member member = new Member("heowc", 14, "010xxxxxxxx");
        final String errorMessageToJson = toJsonString(
                Collections.singletonList(ErrorMessage.of("phone", "올바른 형식의 전화번호여야 합니다"))
        );

        assertResponse(member, status().isBadRequest(), errorMessageToJson);
    }

    private String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private void assertResponse(Member member, ResultMatcher matcher, String result) throws Exception {
        mvc.perform(get("/member")
                .queryParam("name", member.getName())
                .queryParam("age", member.getAge() == null ? null : String.valueOf(member.getAge()))
                .queryParam("phone", member.getPhone())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(matcher)
                .andExpect(content().json(result));
    }
}
