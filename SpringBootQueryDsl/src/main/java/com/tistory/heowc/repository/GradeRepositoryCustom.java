package com.tistory.heowc.repository;

import java.util.List;

import com.tistory.heowc.domain.Grade;

public interface GradeRepositoryCustom {

	List<Grade> findGradeJoinNameOfStudent(String name);
	
	List<Grade> findGradeSubQueryNameOfStudent(String name);
	
	Long deleteByNum(Integer num);
	
	Long setFixedNameByNum(Grade grade);
}
