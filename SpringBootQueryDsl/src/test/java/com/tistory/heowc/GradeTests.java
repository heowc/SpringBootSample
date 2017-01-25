package com.tistory.heowc;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeTests {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Before
	public void beforeTest() {
		System.out.println("============================== Before");
		gradeRepository.save(new Grade(1, "일학년"));
		gradeRepository.save(new Grade(2, "이학년"));
		gradeRepository.save(new Grade(3, "삼학년"));
		
		studentRepository.save(new Student(1, "wonchul", 173.8, 2));
		studentRepository.save(new Student(2, "naeun",   165.2, 2));
		studentRepository.save(new Student(3, "tistory", 160.0, 1));
	}
	
	@Transactional
	@Test
	public void test_findAllGrade() {
		System.out.println("============================== findAllGrade");
		gradeRepository.findAll()
						.stream()
						.map(grade -> grade.toString())
						.forEach(System.out::println);
	}
	
	@Transactional
	@Test
	public void test_findGradeJoinNameOfStudent() {
		System.out.println("============================== findGradeJoinNameOfStudent");
		gradeRepository.findGradeJoinNameOfStudent("wonchul")
						.stream()
						.map(grade -> grade.toString())
						.forEach(System.out::println);
	}
	
	@Transactional
	@Test
	public void test_findGradeSubQueryNameOfStudent() {
		System.out.println("============================== findGradeSubQueryNameOfStudent");
		gradeRepository.findGradeSubQueryNameOfStudent("wonchul")
						.stream()
						.map(grade -> grade.toString())
						.forEach(System.out::println);
	}
	
	@Transactional
	@Test
	public void test_deleteByNum() {
		System.out.println("============================== deleteByNum");
		gradeRepository.deleteByNum(3);
	}
	
	@Transactional
	@Test
	public void test_setFixedNameByNum() {
		System.out.println("============================== setFixedNameByNum");
		gradeRepository.setFixedNameByNum(new Grade(1, "<1>학년"));
	}
}
