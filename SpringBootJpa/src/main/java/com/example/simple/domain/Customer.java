package com.example.simple.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NamedQuery(
		name = "Custom.findByName",
		query = "SELECT c FROM Customer c WHERE c.name = :name ")
public class Customer {
	
	@Id
	@GeneratedValue
	private Long idx;
	
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
