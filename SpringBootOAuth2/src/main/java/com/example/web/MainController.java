package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping(value="/")
	public String index(){
		return "Hello, Spring Boot Oauth2";
	}
}
