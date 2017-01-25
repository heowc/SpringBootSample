package com.tistory.heowc.repository;

import java.util.List;

import com.tistory.heowc.domain.Grade;

public interface GradeRepositoryCustom {

	public List<Grade> findGradeJoinNameOfStudent(String name);
	
	public List<Grade> findGradeSubQueryNameOfStudent(String name);
	
	public Long deleteByNum(Integer num);
	
	public Long setFixedNameByNum(Grade grade);
}
