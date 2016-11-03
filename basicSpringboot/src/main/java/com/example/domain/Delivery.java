package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="office_del")
@Getter @Setter
public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idx;
	private String si;
	private String gu;
	private String dong;
	private String ri;
	private String office;
	private int bunjistart;
	private int bunjiend;
	private int subbunjistart;
	private int subbunjiend;
	private String bldname;
	private String doseo;
	
	protected Delivery() {}
	
	public Delivery(String si, String gu, String dong, String ri){
		this.si		= si;
		this.gu		= gu;
		this.dong	= dong;
		this.ri		= ri;
	}

	@Override
	public String toString() {
		return "Delivery [idx=" + idx + ", si=" + si + ", gu=" + gu + ", dong="
				+ dong + ", ri=" + ri + ", office=" + office + ", bunjistart="
				+ bunjistart + ", bunjiend=" + bunjiend + ", subbunjistart="
				+ subbunjistart + ", subbunjiend=" + subbunjiend + ", bldname="
				+ bldname + ", doseo=" + doseo + "]";
	}
}
