package com.example;

import com.example.service.BasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MockTest {

	@Autowired
	TestRestTemplate restTemplate;

	@MockBean
	BasicService service;

	@Test
	public void test() {
		given(service.test(0))
				.willReturn("Spring Boot Service Test");

		String result = restTemplate.getForObject("/test?flag=0", String.class);
		assertThat(result)
				.isEqualTo("Spring Boot Service Test");
	}

	@Test
	public void mockTest() {
		given(service.test(0))
				.willReturn("Spring Boot Service Test");

		assertThat(service.test(0))
				.isEqualTo("Spring Boot Service Test");
	}
}
