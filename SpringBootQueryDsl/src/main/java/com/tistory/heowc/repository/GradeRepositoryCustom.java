package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Grade;

public interface GradeRepositoryCustom {

	public Grade findGradeByNameOfStudent(String name);
}
