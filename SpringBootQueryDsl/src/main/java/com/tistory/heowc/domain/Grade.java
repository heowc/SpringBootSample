package com.tistory.heowc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Grade {

	@Id @GeneratedValue
	@Column(name = "GRADE_NUM")
	private Integer gradeNum;
	
	@Column(name = "GRADE_NAME")
	private String gradeName;
	
	@OneToMany(mappedBy="grade", targetEntity=Student.class)
	private List<Student> students = new ArrayList<Student>();
	
	public Grade(Integer gradeNum, String gradeName) {
		this.gradeNum  = gradeNum;
		this.gradeName = gradeName;
	}
	
	protected Grade() {}
	
}
