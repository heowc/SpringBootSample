package com.example.web;

import com.example.service.BasicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

@RestController
public class BasicController {

	private final BasicService service;
	private final AsyncRestTemplate asyncRestTemplate;

	private static final Logger logger = Logger.getLogger(BasicController.class);

	@Autowired
	public BasicController(BasicService service, AsyncRestTemplate asyncRestTemplate) {
		this.service = service;
		this.asyncRestTemplate = asyncRestTemplate;
	}

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

	@GetMapping("/{userName}")
	public void getRepository(@PathVariable String userName) {
		getRepositoryInfo(userName)
				.addCallback(
						System.out::println,
						System.out::println
				);
	}

	private ListenableFuture<ResponseEntity<String>> getRepositoryInfo(String userName) {
		return asyncRestTemplate
				.getForEntity("https://api.github.com/users/" + userName + "/repos", String.class);
	}
}
