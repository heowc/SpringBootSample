package com.tistory.heowc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.tistory.heowc.domain.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>, 
											GradeRepositoryCustom,
											QueryDslPredicateExecutor<Grade> {

}
