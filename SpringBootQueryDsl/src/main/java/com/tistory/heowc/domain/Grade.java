package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Grade implements Serializable {

	private static final long serialVersionUID = -4432332213569816450L;

	@Id
	@Column(name = "GRADE_NUM")
	private Integer gradeNum;

	@Column(name = "GRADE_NAME")
	private String gradeName;

	@OneToMany(mappedBy = "grade")
	private List<Student> students = new ArrayList<>();

	protected Grade() {}

	public static Grade of(Integer gradeNum, String gradeName) {
		Grade grade = new Grade();
		grade.setGradeNum(gradeNum);
		grade.setGradeName(gradeName);
		return grade;
	}
}
