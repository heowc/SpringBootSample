package com.tistory.heowc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.tistory.heowc.domain.Student;

public class StudentRepositoryImpl extends QueryDslRepositorySupport implements StudentRepositoryCustom {

	public StudentRepositoryImpl() {
		super(Student.class);
	}
	@Override
	public List<Student> findStudentByName(String name) {
//		QStudent student = QStudent.student;
//		return from(student)
//				.where(student.name.eq(name))
//				.fetch();
		return null;
	}
	
	@Override
	public List<Student> findStudentByGradeAndHeight(Integer grade, Double height) {
//		QStudent student = QStudent.student;
//		return from(student)
//				.where(student.gradeNum.eq(grade).and(student.height.goe(height)))
////				.where(student.gradeNum.eq(grade), student.height.goe(height))  
//				.fetch();
		return null;
	}
}
