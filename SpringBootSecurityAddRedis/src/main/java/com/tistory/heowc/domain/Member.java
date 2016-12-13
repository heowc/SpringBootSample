package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@SuppressWarnings("serial")
@AllArgsConstructor
public class Member implements Serializable {

	@Id
	String id;
	
	String passoword;
	
	protected Member(){}
}
