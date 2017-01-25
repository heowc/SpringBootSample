package com.tistory.heowc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.heowc.domain.QStudent;
import com.tistory.heowc.domain.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Override
	public List<Student> findStudentByName(String name) {
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(student)
				.where(student.name.eq(name))
				.fetch();
	}
	
	@Override
	public Predicate equalName(String name) {
		Path<Student> path			= Expressions.path(Student.class, "student");
		Path<String>  studentName	= Expressions.path(String.class, path, "name");
		Expression<String> eqName	= Expressions.constant(name);
		return Expressions.predicate(Ops.EQ, studentName, eqName);
	}
	
	@Override
	public List<Student> findStudentByGradeAndHeight(Integer grade, Double height) {
		QStudent student = QStudent.student;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(student.gradeNum.eq(grade));
		builder.and(student.height.goe(height));
		
		return queryFactory
				.selectFrom(student)
				.where(builder)  
				.fetch();

//		return queryFactory
//				.selectFrom(student)
//				.where(student.gradeNum.eq(grade), student.height.goe(height))  
//				.fetch();
		
//		return queryFactory
//				.selectFrom(student)
//				.where(student.gradeNum.eq(grade).and(student.height.goe(height)))
//				.fetch();
	}
	
	@Override
	public List<Student> findStudentOrderByName() {
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(student)
				.orderBy(student.name.asc())
				.fetch();
	}
	
	@Override
	public List<Integer> findStudentGroupingByGradeNum() {
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(student)
				.select(student.gradeNum)
				.groupBy(student.gradeNum)
				.fetch();
	}
}
