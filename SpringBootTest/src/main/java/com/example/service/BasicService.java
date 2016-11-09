package com.example.service;

import com.example.domain.TestVo;

public interface BasicService {

	public String test(int flag) throws Exception;
	
	public TestVo jsonTest();
}
