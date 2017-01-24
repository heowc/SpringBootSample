package com.tistory.heowc.repository;

import java.util.List;

import com.tistory.heowc.domain.Student;

public interface StudentRepositoryCustom {

	List<Student> findStudentByName(String name);
	
	List<Student> findStudentByGradeAndHeight(Integer grade, Double height);
}
