package com.tistory.heowc.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

	@Id
	private String id;
	
	private String password;
	
	protected Member() { }

	public Member(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Member{" +
				"id='" + id + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
