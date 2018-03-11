package com.example.simple.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("date")
public class TimeController {

	@GetMapping
	public LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}
}
