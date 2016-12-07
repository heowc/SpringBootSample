package com.example.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.BasicService;

@RestController
public class BasicController {

	@Autowired
	private BasicService service;
	
	private static final Logger logger = Logger.getLogger(BasicController.class);
	
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
}
