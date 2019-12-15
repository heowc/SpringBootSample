package com.tistory.heowc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLogStashApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootLogStashApplicationTests.class);

    @Test
    void test_log() {
        logger.info("test1");
        logger.info("test2");
        logger.info("test3");
        logger.info("test4");
        logger.info("test5");
    }
}
