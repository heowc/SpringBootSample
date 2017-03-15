package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long idx;
	
	@Column(length=50)
	private String name;
	
	@Column(length=14)
	private String tel;
	
	private String bigo;

	protected Customer() {}

	public Customer(String name, String tel, String bigo) {
		this.name = name;
		this.tel = tel;
		this.bigo = bigo;
	}
}
