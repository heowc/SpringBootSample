package com.example.java;

import com.example.java.service.BasicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@SpringBootTest
class SpringBootAsyncApplicationTests {

    @Autowired
    private BasicService service;

    @Test
    void test_async() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> service.onAsync());
    }

    @Test
    void test_sync() {
        assertTimeout(Duration.ofMillis(1100), () -> service.onSync());
    }
}
