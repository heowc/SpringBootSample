package com.tistory.heowc;

import com.tistory.heowc.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class UserRedisTemplateTest {

    @Autowired RedisTemplate<String, User> userRedisTemplate;

    @Test
    public void test1_set() throws Exception {
        userRedisTemplate.opsForValue().set("user", User.getDefaultUser());
    }

    @Test
    public void test2_get() throws Exception {
        log.info(
                userRedisTemplate.opsForValue().get("user").toString()
        );
    }
}
