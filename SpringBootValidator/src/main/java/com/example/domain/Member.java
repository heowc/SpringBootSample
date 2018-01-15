package com.example.domain;

import com.example.validator.Phone;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


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

	@Phone
	private String phone;

	protected Member() {}

	public Member(String name, Integer age, String phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
}

