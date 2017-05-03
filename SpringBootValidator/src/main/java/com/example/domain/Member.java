package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idx;
	
	@NotNull(message="name null")
	private String name;
	
	@NotNull(message="age null")
	@Min(value=14, message="min 14")
	private Integer age;
	
	@NotNull(message="tel null")
	@Size(min=14,max=20, message="no tel")
	private String tel;
}

