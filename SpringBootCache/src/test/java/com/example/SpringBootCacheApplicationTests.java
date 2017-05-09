package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootCacheApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	private long startTime;
	private long endTime;
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests.class);
	
	@Before
	public void onBefore() {
		startTime = System.currentTimeMillis();
	}
	
	@After
	public void onAfter() {
		endTime = System.currentTimeMillis();
		logger.info("소요시간:" + (endTime-startTime) + "ms");
	}
	
	@Test
	public void test1() throws Exception {
		restTemplate.getForObject("/?isbn=a", Book.class);
	}

	@Test
	public void test2() throws Exception {
		restTemplate.getForObject("/?isbn=a", Book.class);
	}
	

	@Test
	public void test3() throws Exception {
		restTemplate.getForObject("/?isbn=b", Book.class);
	}
	
	@Test
	public void test4() throws Exception {
		restTemplate.getForObject("/?isbn=a", Book.class);
	}
	
	@Test
	public void test5() throws Exception {
		restTemplate.getForObject("/clear?isbn=a", String.class);
		restTemplate.getForObject("/?isbn=a", Book.class);
	}
}
