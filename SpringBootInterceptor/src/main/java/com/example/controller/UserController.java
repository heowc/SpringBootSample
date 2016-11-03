package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@GetMapping(value = "/" )
	public String index(){
		String msg = "Hello, User!";
		logger.info(msg);
		return msg;
	}
}
