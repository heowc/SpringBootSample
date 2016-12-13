package com.tistory.heowc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping
	public String getUser() {
		String msg = "Hello, User!!"; 
		System.out.println(msg);
		return msg;
	}
}
