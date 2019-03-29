package com.example.java;

import com.example.java.component.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCacheApplication.class)
public class SpringBootCacheApplicationTests {

	@Autowired
	private BookRepository repository;

	private long startTime;

	private static final Logger logger = LoggerFactory.getLogger(SpringBootCacheApplicationTests.class);

	@Before
	public void onBefore() {
		startTime = System.currentTimeMillis();
	}

	@After
	public void onAfter() {
		logger.info("소요시간: {}ms", System.currentTimeMillis() - startTime);
	}

	@Test
	public void test1() {
		repository.getByIsbn("a");
	}

	@Test
	public void test2() {
		repository.getByIsbn("a");
	}

	@Test
	public void test3() {
		repository.getByIsbn("b");
	}

	@Test
	public void test4() {
		repository.getByIsbn("a");
	}

	@Test
	public void test5() {
		repository.refresh("a");
		repository.getByIsbn("a");
	}
}
