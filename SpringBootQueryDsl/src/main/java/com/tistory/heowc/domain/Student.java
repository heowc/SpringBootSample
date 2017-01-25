package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Student implements Serializable {

	private static final long serialVersionUID = -2575227151310448540L;

	@Id
	@Column(name = "STUDENT_ID") @NonNull
	private Integer id;

	@Column(name = "STUDENT_NAME") @NonNull
	private String  name;
	
	@Column(name = "STUDENT_HEIGHT") @NonNull
	private Double  height;
	
	@Column(name = "GRADE_NUM") @NonNull
	private Integer gradeNum;
	
	protected Student() {}
}
