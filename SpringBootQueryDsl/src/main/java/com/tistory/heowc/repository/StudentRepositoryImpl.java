package com.tistory.heowc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;
import com.tistory.heowc.domain.QStudent;
import com.tistory.heowc.domain.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Student> findStudentByName(String name) {
		JPAQuery<Student> query = new JPAQuery<>(em);
		QStudent student = QStudent.student;
		return query.from(student)
					.where(student.name.eq(name))
					.fetch();
	}
}
