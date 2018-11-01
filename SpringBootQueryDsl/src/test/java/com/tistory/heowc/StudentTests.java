package com.tistory.heowc;

import com.querydsl.core.Tuple;
import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentTests {

	@Autowired StudentRepository studentRepository;
	
	@Autowired GradeRepository gradeRepository;

	@Before
	public void beforeTest() {
		System.out.println("============================== Before");

		Grade firstGrade = gradeRepository.save(Grade.of(1,"일학년"));
		Grade secondGrade = gradeRepository.save(Grade.of(2, "이학년"));
		Grade thirdGrade = gradeRepository.save(Grade.of(3, "삼학년"));

		Student wonchul = Student.of("wonchul", 173.8);
		Student naeun = Student.of("naeun", 165.2);
		Student tistory = Student.of("tistory", 160.0);

		wonchul.setGrade(firstGrade);
		naeun.setGrade(secondGrade);
		tistory.setGrade(thirdGrade);

		studentRepository.save(wonchul);
		studentRepository.save(naeun);
		studentRepository.save(tistory);

		studentRepository.flush();
	}
	
	@Test
	public void test_findAllStudent() {
		// given

		// when
		List<Student> studentList = studentRepository.findAll();

		// then
		assertThat(studentList).size().isEqualTo(3);
	}
	
	@Test
	public void test_findStudentByName() {
		// given

		// when
		List<Student> studentList = studentRepository.findStudentByName("wonchul");
		Iterable<Student> studentIterable = studentRepository.findAll(studentRepository.equalName("wonchul"));
		List<Student> studentList2 = studentRepository.findStudentByNameExtension("wonchul");

		// then
		assertThat(studentList).element(0).satisfies(s -> {
			Student student = studentIterable.iterator().next();

			assertThat(s.getName()).isEqualTo(student.getName());
			assertThat(s.getName()).isEqualTo(studentList2.get(0).getName());

			assertThat(s.getHeight()).isEqualTo(student.getHeight());
			assertThat(s.getHeight()).isEqualTo(studentList2.get(0).getHeight());
		});
	}
	
	@Test
	public void test_findStudentByNameAndHeight() {
		// given
		Student student = Student.of("wonchul", 165.0);

		// when
		List<Student> studentList = studentRepository.findStudentByNameAndHeight(student.getName(), student.getHeight());

		// then
		assertThat(studentList).size().isEqualTo(1);
		assertThat(studentList).element(0).satisfies(s -> {
			assertThat(s.getName()).isEqualTo(student.getName());
			assertThat(s.getHeight()).isGreaterThanOrEqualTo(student.getHeight());
			assertThat(s.getGrade().getGradeNum()).isEqualTo(1);
			assertThat(s.getGrade().getGradeName()).isEqualTo("일학년");
		});
	}
	
	@Test
	public void test_findStudentOrderByName() {
		// given

		// when
		List<Student> studentList = studentRepository.findStudentOrderByName();

		// then
		assertThat(studentList).element(0).satisfies(s -> assertThat(s.getName()).isEqualTo("naeun"));
		assertThat(studentList).element(1).satisfies(s -> assertThat(s.getName()).isEqualTo("tistory"));
		assertThat(studentList).element(2).satisfies(s -> assertThat(s.getName()).isEqualTo("wonchul"));
	}
	
	@Test
	public void test_findStudentGroupingByGradeNum() {
		// given

		// when
		List<Integer> list = studentRepository.findStudentGroupingByGradeNum();

		// then
		assertThat(list).contains(1, 2, 3);
	}
	
	@Test
	public void test_findCaseStudentAll() {
		// given

		// when
		List<Tuple> studentList = studentRepository.findCaseStudentAll_Tuple();

		// then
		assertThat(studentList).allSatisfy(s -> assertThat(s.get(0, String.class)).isEqualTo("저학년"));
	}

	@After
	public void after_clear() {
		studentRepository.deleteAll();
		gradeRepository.deleteAll();
	}
}
