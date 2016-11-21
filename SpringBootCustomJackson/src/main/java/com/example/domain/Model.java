package com.example.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings({ "unused", "serial" })
@Getter @Setter
public class Model implements Serializable {

	private String name;
	private int type;

	public Model(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	private Model() {
	}
	
	@Override
	public String toString() {
		return "Model [name=" + name + ", type=" + type + "]";
	}
}
