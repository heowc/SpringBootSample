package com.example.domain;

import com.example.validation.Phone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Member {

	private long idx;

	@NotNull
	private String name;

	@NotNull
	@Min(14)
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

