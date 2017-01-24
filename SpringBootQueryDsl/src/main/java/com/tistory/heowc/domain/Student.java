package com.tistory.heowc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Student {

	@Id @GeneratedValue
	@Column(name = "STUDENT_ID")
	private Integer id;

	@Column(name = "STUDENT_NAME")
	private String  name;
	
	@Column(name = "STUDENT_HEIGHT")
	private Double  height;
	
	@Column(name = "GRADE_NUM")
	private Integer gradeNum;
	
	public Student(Integer id, String name, Double height, Integer gradeNum) {
		this.id       = id;
		this.name     = name;
		this.height   = height;
		this.gradeNum = gradeNum;
	}
	
	@ManyToOne(targetEntity=Grade.class)
	@JoinColumn(referencedColumnName = "GRADE_NUM")
	private Grade grade;
	
	protected Student() {}
	
}
