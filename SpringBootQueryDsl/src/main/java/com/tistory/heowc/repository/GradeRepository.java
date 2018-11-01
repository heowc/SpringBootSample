package com.tistory.heowc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tistory.heowc.domain.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>,
        GradeRepositoryCustom,
        QuerydslPredicateExecutor<Grade> {

}
