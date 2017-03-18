package com.tistory.heowc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GradeTests {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Before
	public void beforeTest() {
		System.out.println("============================== Before");

		Grade firstGrade = new Grade("일학년");
		Grade secondGrade = new Grade("이학년");
		Grade thirdGrade = new Grade("삼학년");

		Student wonchul = new Student("wonchul", 173.8);
		Student naeun = new Student("naeun", 165.2);
		Student tistory = new Student("tistory", 160.0);

		firstGrade.getStudents().add(wonchul);
		secondGrade.getStudents().add(naeun);
		thirdGrade.getStudents().add(tistory);

		gradeRepository.save(firstGrade);
		gradeRepository.save(secondGrade);
		gradeRepository.save(thirdGrade);

		studentRepository.save(wonchul);
		studentRepository.save(naeun);
		studentRepository.save(tistory);

		entityManager.flush();
		entityManager.clear();
	}
	
	@Test
	public void test_findAllGrade() {
		System.out.println("============================== findAllGrade");
		gradeRepository.findAll()
						.forEach(System.out::println);

	}
	
	@Test
	public void test_findGradeJoinNameOfStudent() {
		System.out.println("============================== findGradeJoinNameOfStudent");
		gradeRepository.findGradeJoinNameOfStudent("wonchul")
						.forEach(System.out::println);
	}
	
	@Test
	public void test_findGradeSubQueryNameOfStudent() {
		System.out.println("============================== findGradeSubQueryNameOfStudent");
		gradeRepository.findGradeSubQueryNameOfStudent("wonchul")
						.forEach(System.out::println);
	}
	
	@Test
	public void test_deleteByNum() {
		System.out.println("============================== deleteByNum");
		gradeRepository.deleteByNum(3);
	}
	
	@Test
	public void test_setFixedNameByNum() {
		System.out.println("============================== setFixedNameByNum");
		gradeRepository.setFixedNameByNum(new Grade(1,"<1>학년", null));
	}
}
