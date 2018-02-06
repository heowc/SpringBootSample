package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@GetMapping
	public ResponseEntity<?> index() {
		String msg = "Hello, User!";
		logger.info(msg);
		return ResponseEntity.ok()
					.header("controller", "controller")
					.body(msg);
	}
}
