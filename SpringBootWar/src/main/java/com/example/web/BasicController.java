package com.example.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@GetMapping("${message}")
	public String getMessage(@RequestParam String message) {
		return message;
	}
}
