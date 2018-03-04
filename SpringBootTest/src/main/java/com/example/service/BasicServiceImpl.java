package com.example.service;

import com.example.domain.TestMessage;
import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl implements BasicService {

	@Override
	public String test(int flag) {
		return flag == 0 ? "Spring Boot Service Test" : "";
	}

	@Override
	public TestMessage jsonTest() {
		return new TestMessage("wonchul", 0);
	}
}
