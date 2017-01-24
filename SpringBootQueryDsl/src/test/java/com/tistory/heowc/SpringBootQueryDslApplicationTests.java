package com.tistory.heowc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootQueryDslApplicationTests {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Before
	public void beforeTest() {
		System.out.println("============================== Before");
		gradeRepository.save(new Grade(1, "1 학년"));
		gradeRepository.save(new Grade(2, "2 학년"));
		gradeRepository.save(new Grade(3, "3 학년"));
		
		studentRepository.save(new Student(1, "wonchul", 173.8, 1));
		studentRepository.save(new Student(2, "naeun",   165.2, 1));
		studentRepository.save(new Student(3, "tistory", 160.0, 2));
	}
	
	@Test
	public void test_findStudentByName() {
		System.out.println("============================== findStudentByName");
//		studentRepository.findStudentByName("wonchul")
//							.stream()
//							.map(student -> student.toString())
//							.forEach(System.out::println);
	}
	
	@Test
	public void test_findStudentByGradeAndHeight() {
		System.out.println("============================== findStudentByGradeAndHeight");
//		studentRepository.findStudentByGradeAndHeight(1, 165.0)
//							.stream()
//							.map(student -> student.toString())
//							.forEach(System.out::println);
	}
	
	@Test
	public void test_findGradeByNameOfStudent() {
		System.out.println("============================== findGradeByNameOfStudent");
		System.out.println(gradeRepository.findGradeByNameOfStudent("wonchul"));
	}
}
