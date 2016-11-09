package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.TestVo;

@Service
public class BasicServiceImpl implements BasicService {

	@Override
	public String test(int flag) throws Exception {
		
		if(flag == 0) {
			return "Spring Boot Service Test";
		}
		
		return null;
	}

	@Override
	public TestVo jsonTest() {
		return new TestVo("wonchul", 0);
	}
}
