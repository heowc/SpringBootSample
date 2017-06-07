package com.tistory.heowc;

import com.tistory.heowc.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class UserRedisTemplateTest {

    @Autowired RedisTemplate<String, User> userRedisTemplate;

    @Resource(name = "userRedisTemplate") ListOperations<String, User> listOps;

    @Test
    public void test1_push() throws Exception {
        listOps.leftPush("user", User.getDefaultUser());
    }

    @Test
    public void test1_pop() throws Exception {
        log.info(
                listOps.rightPop("user").toString()
        );
    }
}
