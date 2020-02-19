package com.example.java;

import com.example.java.component.SimpleAspect;
import com.example.java.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootAopApplicationTests {

	@Autowired
	private TestService service;

	@Test
	void test_aopAndNoAop() {
		assertThat(SimpleAspect.count()).isEqualTo(0);
		service.testAop();
		assertThat(SimpleAspect.count()).isEqualTo(1);
		service.test();
		assertThat(SimpleAspect.count()).isEqualTo(1);
	}
}
