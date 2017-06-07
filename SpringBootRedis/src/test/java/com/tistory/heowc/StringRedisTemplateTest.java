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

	private static final String KEY_1 = "set_get";

	@Autowired StringRedisTemplate stringRedisTemplate;

	@Test
	public void test1_set() throws Exception {
		stringRedisTemplate.opsForValue().set(KEY_1, "wonchul");
	}

	@Test
	public void test2_get() throws Exception {
		log.info(
				String.format("pop [ %s ]", stringRedisTemplate.opsForValue().get(KEY_1))
		);
		stringRedisTemplate.delete(KEY_1);
	}
}