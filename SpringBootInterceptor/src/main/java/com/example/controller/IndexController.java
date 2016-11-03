package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@GetMapping(value = "/")
	public String index(){
		String msg = "Hello, Spring Boot Interceptor";
		logger.info(msg);
		return msg;
	}
}
