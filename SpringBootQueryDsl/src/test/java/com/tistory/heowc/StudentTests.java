package com.tistory.heowc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.types.dsl.PathBuilder;
import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTests {

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
	
	@Test
	public void test_findAllStudent() {
		System.out.println("============================== findAllStudent");
		studentRepository.findAll()
							.stream()
							.map(student -> student.toString())
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentByName() {
		System.out.println("============================== findStudentByName");
		studentRepository.findStudentByName("wonchul")
							.stream()
							.map(student -> student.toString())
							.forEach(System.out::println);
		
		studentRepository.findAll(studentRepository.equalName("wonchul"))
							.forEach(student -> System.out.println(student.toString()));
	}
	
	@Test
	public void test_findStudentByGradeAndHeight() {
		System.out.println("============================== findStudentByGradeAndHeight");
		studentRepository.findStudentByGradeAndHeight(1, 165.0)
							.stream()
							.map(student -> student.toString())
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentOrderByName() {
		System.out.println("============================== findStudentOrderByName");
		studentRepository.findStudentOrderByName()
							.stream()
							.map(student -> student.toString())
							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentGroupingByGradeNum() {
		System.out.println("============================== findStudentGroupingByGradeNum");
		studentRepository.findStudentGroupingByGradeNum()
							.stream()
							.map(student -> "학년 : " + student.toString())
							.forEach(System.out::println);
	}
	
	@Test
	public void test() {
		System.out.println("============================== test");
		PathBuilder<Student> studentPath = new PathBuilder<>(Student.class, "student");
		
		studentPath.get("student_name", String.class);
		
	}
}
