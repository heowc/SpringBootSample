package com.tistory.heowc.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class Page {

	private Integer pageNo;
	private Integer recodeSize;
	private Long totalSize;
	
	@JsonIgnore
	private Integer top;
}
