package com.tistory.heowc.repository;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.QGrade;
import com.tistory.heowc.domain.QStudent;

public class GradeRepositoryImpl extends QueryDslRepositorySupport implements GradeRepositoryCustom {

	public GradeRepositoryImpl() {
		super(Grade.class);
	}

	@Override
	public Grade findGradeByNameOfStudent(String name) {
		QGrade grade = QGrade.grade;
		QStudent student = QStudent.student;
		return from(grade)
				.join(grade.students, student)
				.where(student.name.eq(name))
				.fetchOne();
//		return null;
	}
	
	/*
	 * select		grade_num, grade_name 
	 * from			grade
	 * inner join	student 
	 * on			grade.grade_num = student.grade_grade_num 
	 * where		student.name=?
	 */
}
