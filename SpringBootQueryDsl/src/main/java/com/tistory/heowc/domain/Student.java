package com.tistory.heowc.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Student {

	@Id
	private Integer stdentId;
	private String  name;
	private Double  height;
	private Integer grade;
	
	protected Student() {}
	
}
