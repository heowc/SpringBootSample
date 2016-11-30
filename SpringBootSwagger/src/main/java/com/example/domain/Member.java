package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
public class Member implements Serializable {

	@Id
	private String id;
	
	private String pw;
	
	private String name;
	
	private String tel;
}
