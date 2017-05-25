package com.tistory.heowc.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.heowc.domain.QGrade;
import com.tistory.heowc.domain.QStudent;
import com.tistory.heowc.domain.Student;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	@Override
	public Predicate equalName(String name) {
		Path<Student> path			= Expressions.path(Student.class, "student");
		Path<String>  studentName	= Expressions.path(String.class, path, "name");
		Expression<String> equalName= Expressions.constant(name);
		return Expressions.predicate(Ops.EQ, studentName, equalName);
	}

	/*
	 select student_id, grade_num, student_height, student_name
	 from 	student
	 where 	student_name=?
	 */

	@Override
	public List<Student> findStudentByName(String name) {
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(student)
				.where(student.name.eq(name))
				.fetch();
	}

	/*
	 select student_id, grade_num, student_height, student_name
	 from 	student
	 where 	student_name=?
	 */
	
	@Override
	public List<Student> findStudentByNameExtension(String name) {
		PathBuilder<Student> studentPath = new PathBuilder<>(Student.class, "student");
		
		return queryFactory.selectFrom(studentPath)
				.where(studentPath.get("name").eq(name))
				.fetch();
	}

	/*
	 select student_id, grade_num, student_height, student_name
	 from 	student
	 where 	student_name=? and student_height>=?
	 */

	@Override
	public List<Student> findStudentByNameAndHeight(String name, Double height) {
		QStudent student = QStudent.student;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(student.name.eq(name));
		builder.and(student.height.goe(height));
		
		return queryFactory
				.selectFrom(student)
				.where(builder)
				.fetch();

//		return queryFactory
//				.selectFrom(student)
//				.where(student.name.eq(name), student.height.goe(height))
//				.fetch();
		
//		return queryFactory
//				.selectFrom(student)
//				.where(student.name.eq(name).and(student.height.goe(height)))
//				.fetch();
	}

	/*
	 select 	student_id, grade_num, student_height, student_name
	 from 		student
	 order by 	student0_.student_name asc
	 */

	@Override
	public List<Student> findStudentOrderByName() {
		QStudent student = QStudent.student;
		return queryFactory
				.selectFrom(student)
				.orderBy(student.name.asc())
				.fetch();
	}

	/*
	select		grade.grade_num
	from		grade
	inner join	student
	on 			grade.grade_num=student.grade_num
	group by	grade.grade_num
	 */

	@Override
	public List<Integer> findStudentGroupingByGradeNum() {
		QStudent student = QStudent.student;
		QGrade grade = QGrade.grade;
		return queryFactory
				.select(grade.gradeNum)
				.from(grade)
				.join(grade.students, student)
				.groupBy(grade.gradeNum)
				.fetch();
	}

	/*
	 select 	case
	 			when grade0_.grade_num>? then '고학년'
	 			else '저학년'
	 			end,
	 			student.student_id,
	 			student.student_name,
	 			student.student_height
	 from 		grade
	 inner join student
	 on 		grade.grade_num=student.grade_num
	 */

	@Override
	public List<Tuple> findCaseStudentAll_Tuple() {
		QStudent student = QStudent.student;
		QGrade grade = QGrade.grade;
		Expression<String> cases = new CaseBuilder()
				.when(grade.gradeNum.gt(3)).then("고학년")
				.otherwise("저학년");

		return queryFactory
				.select(cases, student.id, student.name, student.height)
				.from(grade)
				.join(grade.students, student)
				.fetch();
	}

	@Override
	public List<Student> findCaseStudentAll_Student() {
		QStudent student = QStudent.student;
		QGrade grade = QGrade.grade;
		Expression<Integer> cases = new CaseBuilder()
			.when(grade.gradeNum.gt(3)).then(1)
		 	.otherwise(-1);

		return queryFactory
				.select(Projections.fields(Student.class, student.id, student.name, student.height, cases))
//				.select(Projections.bean(Student.class, student.id, student.name, student.height, cases))
				.from(grade)
				.join(grade.students, student)
				.fetch();
	}
}
