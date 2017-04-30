package com.tistory.heowc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWebFluxApplicationTests {

	private WebTestClient webClient;

	@Before
	public void before_init() throws Exception {
		webClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080/").build();
	}

	@Test
	public void test_getMessage() {
		webClient.get()
				.uri("message/{idx}", 1L)
				.exchange().expectBody()
				.consumeAsStringWith(System.out::println);
	}

}
