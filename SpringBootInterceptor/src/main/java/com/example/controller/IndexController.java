package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping(value = "/")
	public ResponseEntity<?> index() {
		String msg = "Hello, Spring Boot Interceptor";
		logger.info(msg);
		return ResponseEntity.ok()
				.header("controller", "controller")
				.body(msg);
	}
}