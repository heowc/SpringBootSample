package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public String test() {
		String msg = "Hello, Spring Boot No AOP";
		logger.info(msg);
		return msg;
	}

	@Override
	public String testAop() {
		String msg = "Hello, Spring Boot AOP";
		logger.info(msg);
		return msg;
	}
}
