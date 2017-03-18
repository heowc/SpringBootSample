package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@RequiredArgsConstructor
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

	@Column(name = "STUDENT_NAME") @NonNull
	private String  name;
	
	@Column(name = "STUDENT_HEIGHT") @NonNull
	private Double  height;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRADE_NUM")
	private Grade grade;

	protected Student() {}
}
