package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
public class Member implements Serializable {

	@Id
	private String id;
	
	private String role;
	
	public Member(String id) {
		this.id = id;
	}
	
	protected Member() {}
}
