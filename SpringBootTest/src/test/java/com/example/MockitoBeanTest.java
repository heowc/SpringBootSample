package com.example;

import com.example.service.BasicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MockitoBeanTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockitoBean
    BasicService service;

    @Test
    public void test() {
        given(service.test(0))
                .willReturn("Spring Boot Service Test");

        final String result = restTemplate.getForObject("/test?flag=0", String.class);
        assertThat(result)
                .isEqualTo("Spring Boot Service Test");
    }

    @Test
    public void mockTest() {
        given(service.test(0))
                .willReturn("Spring Boot Service Test");

        assertThat(service.test(0))
                .isEqualTo("Spring Boot Service Test");
    }
}
