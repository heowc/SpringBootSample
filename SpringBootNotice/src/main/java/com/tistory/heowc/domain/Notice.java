package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@SuppressWarnings("serial")
@AllArgsConstructor
public class Notice implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long	idx;
	private String	title;
	
	@Column(columnDefinition = "text")
	private String	content;
	
	protected Notice() {}
}
