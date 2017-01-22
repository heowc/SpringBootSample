package com.tistory.heowc.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Grade {

	@Id
	private Integer grade;
	private String name;
	
	protected Grade() {}
	
}
