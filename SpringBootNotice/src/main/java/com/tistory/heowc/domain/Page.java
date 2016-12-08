package com.tistory.heowc.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class Page {

	private int pageNo;
	private int recodeSize;
	private long totalSize;
	
	@JsonIgnore
	private int top;
}
