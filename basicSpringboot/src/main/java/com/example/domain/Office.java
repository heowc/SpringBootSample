package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="office")
@Getter @Setter
public class Office implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idx;
	private String name;
	private String addr;
	private String tel;
	private String fax;
	
	protected Office () {}
	
	public Office (String name) {
		this.name = name;
	}
}
