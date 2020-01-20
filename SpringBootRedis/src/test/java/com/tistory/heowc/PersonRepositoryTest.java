package com.tistory.heowc;

import com.tistory.heowc.domain.Person;
import com.tistory.heowc.domain.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.embedded.RedisServer;

import java.io.IOException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class PersonRepositoryTest {

    private static RedisServer redisServer;
    private static final String KEY = "1";

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
    private PersonRepository repository;

    @Test
    void test1_set() {
        repository.save(new Person(KEY, "wonchul", "heo"));
    }

    @Test
    void test2_get() {
        repository.deleteById(KEY);
    }
}
