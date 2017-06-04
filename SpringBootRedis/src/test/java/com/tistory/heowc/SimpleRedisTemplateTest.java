package com.tistory.heowc;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class SimpleRedisTemplateTest {

	private static final String KEY = "test_key";

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;

	@Test
	public void test1_set() throws Exception {
		valueOps.set(KEY, "wonchul");
	}

	@Test
	public void test2_get() throws Exception {
		System.out.println(String.format("pop [ %s ]", valueOps.get(KEY)));
		redisTemplate.delete(KEY);
	}
}