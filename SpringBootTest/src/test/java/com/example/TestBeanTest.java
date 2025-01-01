package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.domain.TestMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.convention.TestBean;

import com.example.service.BasicService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestBeanTest {

    @Autowired
    TestRestTemplate restTemplate;

    @TestBean
    BasicService basicService;

    static BasicService basicService() {
        return new BasicService() {
            @Override
            public String test(int flag) {
                return "Spring Boot Service Test";
            }

            @Override
            public TestMessage jsonTest() {
                throw new UnsupportedOperationException("jsonTest not implemented");
            }
        };
    }

    @Test
    public void mvcTest() {
        final String result = restTemplate.getForObject("/test?flag=0", String.class);
        assertThat(result)
                .isEqualTo("Spring Boot Service Test");
    }

    @Test
    public void serviceTest() {
        assertThat(basicService.test(0))
                .isEqualTo("Spring Boot Service Test");
    }
}
