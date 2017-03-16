package com.example.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "idx", name = "idx")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "idx")
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
