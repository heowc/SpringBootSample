package com.example.java.web;

import com.example.java.domain.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

	//@CrossOrigin
	@GetMapping("{value}")
	public String get(@PathVariable String value) {
		return value;
	}

	@PostMapping
	public Message post(@RequestBody Message message) {
		return message;
	}
}
