package com.example.web;

import com.example.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class BasicController {

	@Autowired
	private BasicService service;

	private Logger logger = LoggerFactory.getLogger(BasicController.class);

	@GetMapping("/async")
	public String goAsync() {
		service.onAsync();
		String str = "Hello Spring Boot Async!!";
		logger.info(str);
		logger.info("==================================");
		return str;
	}

	@GetMapping("/sync")
	public String goSync() {
		service.onSync();
		String str = "Hello Spring Boot Sync!!";
		logger.info(str);
		logger.info("==================================");
		return str;
	}

	private WebClient webClient = WebClient.builder()
			.baseUrl("https://api.github.com")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			.build();

	@GetMapping("/{userName}")
	public Mono<List> showGithubReposByUserName(@PathVariable String userName) {
		return webClient.get()
				.uri(String.format("/users/%s/repos", userName))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.retrieve()
				.bodyToMono(List.class);
	}
}
