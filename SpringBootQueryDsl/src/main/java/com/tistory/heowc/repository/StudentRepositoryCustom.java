package com.tistory.heowc.repository;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.tistory.heowc.domain.Student;

public interface StudentRepositoryCustom {

	Predicate equalName(String name);
	
	List<Student> findStudentByName(String name);
	
	List<Student> findStudentByGradeAndHeight(Integer grade, Double height);
	
	List<Student> findStudentOrderByName();
	
	List<Integer> findStudentGroupingByGradeNum();
}
