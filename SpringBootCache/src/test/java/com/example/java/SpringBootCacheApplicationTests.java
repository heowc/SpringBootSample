package com.example.java;

import com.example.java.component.BookRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootCacheApplication.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class SpringBootCacheApplicationTests {

    @Autowired
    private BookRepository repository;

    private long startTime;

    private static final Logger logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests.class);

    @BeforeEach
    void onBefore() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void onAfter() {
        logger.info("소요시간: {}ms", System.currentTimeMillis() - startTime);
    }

    @Test
    void test1() {
        repository.getByIsbn("a");
    }

    @Test
    void test2() {
        repository.getByIsbn("a");
    }

    @Test
    void test3() {
        repository.getByIsbn("b");
    }

    @Test
    void test4() {
        repository.getByIsbn("a");
    }

    @Test
    void test5() {
        repository.refresh("a");
        repository.getByIsbn("a");
    }
}
