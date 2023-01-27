package com.tistory.heowc.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Data
@GenericGenerator(
		name = "StudentSequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "sequence_name", value = "STUDENT_SEQ"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
		}
)
public class Student implements Serializable {

	private static final long serialVersionUID = -2575227151310448540L;

	@Id @GeneratedValue(generator = "StudentSequenceGenerator")
	@Column(name = "STUDENT_ID")
	private Integer id;

	@Column(name = "STUDENT_NAME")
	private String  name;

	@Column(name = "STUDENT_HEIGHT")
	private Double  height;

	@Setter(AccessLevel.NONE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRADE_NUM")
	private Grade grade;

	protected Student() {}

	public static Student of(String name, Double height) {
		Student student = new Student();
		student.setName(name);
		student.setHeight(height);
		return student;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
		grade.getStudents().add(this);
	}
}
