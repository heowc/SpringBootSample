package com.tistory.heowc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tistory.heowc.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,
											StudentRepositoryCustom,
											QueryDslPredicateExecutor<Student> {

}
