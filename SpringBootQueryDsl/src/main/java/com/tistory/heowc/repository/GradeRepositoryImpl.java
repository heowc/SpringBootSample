package com.tistory.heowc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.QGrade;
import com.tistory.heowc.domain.QStudent;

public class GradeRepositoryImpl implements GradeRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;
	
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

	/*
	 delete from grade where grade_num=?
	*/

	@Override
	public Long deleteByNum(Integer num) {
		QGrade grade = QGrade.grade;
		return queryFactory
				.delete(grade)
				.where(grade.gradeNum.eq(num))
				.execute();
	}

	/*
	 update grade set grade_name=? where grade_num=?
	 */

	@Override
	public Long setFixedNameByNum(Grade grade) {
		QGrade _grade = QGrade.grade;
		
		return queryFactory
				.update(_grade)
				.where(_grade.gradeNum.eq(grade.getGradeNum()))
				.set(_grade.gradeName, grade.getGradeName())
				.execute();
	}
}
