package com.tistory.heowc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Grade implements Serializable {

	private static final long serialVersionUID = -4432332213569816450L;

	@Id
	@Column(name = "GRADE_NUM") @NonNull
	private Integer gradeNum;
	
	@Column(name = "GRADE_NAME") @NonNull
	private String gradeName;
	
	@OneToMany
	@JoinColumn(name = "GRADE_NUM")
	private List<Student> students;
	
	protected Grade() {}
}
