package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idx;
	
	@JsonProperty
	@NotNull(message="name null")
	private String name;
	
	@JsonProperty
	@NotNull(message="age null")
	@Min(value=14, message="min 14")
	private Integer age;
	
	@JsonProperty
	@NotNull(message="tel null")
	@Size(min=14,max=20, message="no tel")
	private String tel;

	@Override
	public String toString() {
		return "Member [idx=" + idx
				+ ", name=" + name
				+ ", age=" + age
				+ ", tel=" + tel
				+ "]";
	}
}

