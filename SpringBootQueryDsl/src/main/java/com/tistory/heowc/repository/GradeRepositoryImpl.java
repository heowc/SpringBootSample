package com.tistory.heowc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.QGrade;
import com.tistory.heowc.domain.QStudent;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GradeRepositoryImpl implements GradeRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	/*
	 select 	grade.grade_num, grade.grade_name
	 from 		grade
	 inner join	student
	 on 		grade.grade_num=student.grade_num
	 where 		student.student_name=?
	 */

	@Override
	public List<Grade> findGradeJoinNameOfStudent(String name) {
		QGrade grade = QGrade.grade;
		QStudent student = QStudent.student;

		return queryFactory
				.selectFrom(grade)
				.join(grade.students, student)
				.where(student.name.eq(name))
				.fetch();
	}

	/*
	 select grade.grade_num, grade.grade_name
	 from 	grade
	 where 	grade.grade_num=(select		grade1_.grade_num
							 from 		grade grade1_
							 inner join student
							 on 		grade1_.grade_num=student.grade_num
							 where 		student.student_name=?)
	 */

	@Override
	public List<Grade> findGradeSubQueryNameOfStudent(String name) {
		QGrade grade = QGrade.grade;
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(grade)
				.where(grade.gradeNum.eq(queryFactory
										.select(grade.gradeNum)
										.from(grade)
										.join(grade.students, student)
										.where(student.name.eq(name)))
				)
				.fetch();
	}
}
