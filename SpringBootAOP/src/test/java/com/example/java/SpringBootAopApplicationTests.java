package com.example.java;

import com.example.java.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAopApplicationTests {

	@Autowired
	private TestService service;

	@Test
	public void test_aop() {
		service.testAop();
	}

	@Test
	public void test_noAop() {
		service.test();
	}

}
