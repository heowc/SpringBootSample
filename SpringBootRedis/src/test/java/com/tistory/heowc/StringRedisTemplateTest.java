package com.tistory.heowc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@Slf4j
class StringRedisTemplateTest {

    private static final String KEY = "string";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test1_set() {
        stringRedisTemplate.opsForValue().set(KEY, "wonchul");
    }

    @Test
    void test2_get() {
        log.info(
                String.format("pop [ %s ]", stringRedisTemplate.opsForValue().get(KEY))
        );
        stringRedisTemplate.delete(KEY);
    }
}