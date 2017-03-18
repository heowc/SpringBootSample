package com.example.simple.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@GenericGenerator(
		name = "CustomerSequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDER_SEQ"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
		}
)
public class Customer {
	
	@Id
	@GeneratedValue(generator = "CustomerSequenceGenerator")
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
