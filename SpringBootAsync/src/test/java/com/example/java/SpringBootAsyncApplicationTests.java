package com.example.java;

import com.example.java.service.BasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAsyncApplicationTests {

	@Autowired
	private BasicService service;

	@Test
	public void test_async() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		service.onAsync();
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}

	@Test
	public void test_sync() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		service.onSync();
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
}
