package com.tistory.heowc;

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
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class StringRedisTemplateTest {

	private static final String KEY = "test_string";

	@Autowired StringRedisTemplate stringRedisTemplate;

	@Test
	public void test1_push() throws Exception {
		stringRedisTemplate.opsForValue().set(KEY, "wonchul");
	}

	@Test
	public void test2_pop() throws Exception {
		System.out.println(String.format("pop [ %s ]", stringRedisTemplate.opsForValue().get(KEY)));
	}

}
