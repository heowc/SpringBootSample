package com.example.java.domain;

public class Model {

	private String name;
	private int type;

	protected Model() { }

	public Model(String name, int type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", type=" + type + "]";
	}
}
