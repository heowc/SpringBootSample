package com.tistory.heowc;

import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentTests {

	@PersistenceContext EntityManager entityManager;

	@Autowired StudentRepository studentRepository;
	
	@Autowired GradeRepository gradeRepository;

	@Before
	public void beforeTest() {
		System.out.println("============================== Before");
		Grade firstGrade = Grade.of(1, "일학년");
		Grade secondGrade = Grade.of(2, "이학년");
		Grade thirdGrade = Grade.of(3, "삼학년");

		Student wonchul = Student.of("wonchul", 173.8);
		Student naeun = Student.of("naeun", 165.2);
		Student tistory = Student.of("tistory", 160.0);

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
	}
	
	@Test
	public void test_findAllStudent() {
		System.out.println("============================== findAllStudent");
		studentRepository.findAll()
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentByName() {
		System.out.println("============================== findStudentByName");
		studentRepository.findStudentByName("wonchul")
							.forEach(System.out::println);
		
		studentRepository.findAll(studentRepository.equalName("wonchul"))
							.forEach(System.out::println);
		
		studentRepository.findStudentByNameExtension("wonchul")
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentByNameAndHeight() {
		System.out.println("============================== test_findStudentByNameAndHeight");
		studentRepository.findStudentByNameAndHeight("wonchul", 165.0)
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentOrderByName() {
		System.out.println("============================== findStudentOrderByName");
		studentRepository.findStudentOrderByName()
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentGroupingByGradeNum() {
		System.out.println("============================== findStudentGroupingByGradeNum");
		studentRepository.findStudentGroupingByGradeNum()
							.stream()
							.map(gradeNum -> "학년 : " + gradeNum)
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findCaseStudentAll() {
		System.out.println("============================== findCaseStudentAll");
		studentRepository.findCaseStudentAll_Tuple()
							.forEach(System.out::println);
		
//		studentRepository.findCaseStudentAll_Student()
//							.forEach(System.out::println);
	}
}
