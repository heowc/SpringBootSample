package com.example.domain;

import lombok.Getter;

@Getter
public class TestVo {

	String	name;
	int		type;
	
	public TestVo(String name, int type) {
		this.name = name;
		this.type = type;
	}
}
