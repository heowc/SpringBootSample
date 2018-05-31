package com.tistory.heowc;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class StringRedisTemplateTest {

    private static final String KEY = "string";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1_set() {
        stringRedisTemplate.opsForValue().set(KEY, "wonchul");
    }

    @Test
    public void test2_get() {
        log.info(
                String.format("pop [ %s ]", stringRedisTemplate.opsForValue().get(KEY))
        );
        stringRedisTemplate.delete(KEY);
    }
}