package com.example.web;

import com.example.domain.TestMessage;
import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@Autowired
	private BasicService service;

	@GetMapping("/test")
	public String test(@RequestParam("flag") int flag) {
		return service.test(flag);
	}

	@GetMapping("/jsonTest")
	public TestMessage jsonTest() {
		return service.jsonTest();
	}
}
