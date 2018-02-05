package com.example;

import com.example.component.BookRepository;
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
@SpringBootTest
public class SpringBootCacheApplicationTests {

	@Autowired
	private BookRepository repository;

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
		logger.info("소요시간: {}ms", endTime-startTime);
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
