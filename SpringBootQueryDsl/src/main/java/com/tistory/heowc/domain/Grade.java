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
@GenericGenerator(
		name = "GradeSequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GRADE_SEQ"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
		}
)
public class Grade implements Serializable {

	private static final long serialVersionUID = -4432332213569816450L;

	@Id @GeneratedValue(generator = "GradeSequenceGenerator")
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
