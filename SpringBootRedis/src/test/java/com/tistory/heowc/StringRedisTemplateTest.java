package com.tistory.heowc;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.embedded.RedisServer;

import java.io.IOException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class StringRedisTemplateTest {

    private static RedisServer redisServer;
    private static final String KEY = "string";

    @BeforeAll
    static void start() {
        try {
            redisServer = new RedisServer(6379);
            redisServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void end() {
        redisServer.stop();
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test1_set() {
        stringRedisTemplate.opsForValue().set(KEY, "wonchul");
    }

    @Test
    void test2_get() {
        stringRedisTemplate.delete(KEY);
    }
}