package com.example.domain;

import com.example.validator.Phone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idx;

	@NotNull(message = "name null")
	private String name;

	@NotNull(message = "age null")
	@Min(value = 14, message = "min 14")
	private Integer age;

	@Phone
	private String phone;

	protected Member() { }

	public Member(String name, Integer age, String phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

