package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.TestVo;
import com.example.service.BasicService;

@RestController
public class BasicController {

	@Autowired
	BasicService service;
	
	@GetMapping("/")
	public String index(){
		return "Spring Boot Testing!!";
	}
	
	@GetMapping("/test")
	public String test(@RequestParam("flag") int flag) throws Exception {
		return service.test(flag);
	}
	
	@GetMapping("/jsonTest")
	public TestVo jsonTest() throws Exception {
		return service.jsonTest();
	}
}
