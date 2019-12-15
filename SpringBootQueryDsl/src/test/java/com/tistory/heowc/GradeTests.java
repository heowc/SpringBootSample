package com.tistory.heowc;

import com.tistory.heowc.domain.Grade;
import com.tistory.heowc.domain.Student;
import com.tistory.heowc.repository.GradeRepository;
import com.tistory.heowc.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GradeTests {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeRepository gradeRepository;

    @BeforeEach
	void before_setup() {
        System.out.println("============================== Before");

        Grade firstGrade = gradeRepository.save(Grade.of(1, "일학년"));
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
    void test_findAllGrade() {
        // given

        // when
        List<Grade> gradeList = gradeRepository.findAll();

        // then
        assertThat(gradeList).size().isEqualTo(3);
    }

    @Test
	void test_findGradeJoinNameOfStudent() {
        // given

        // when
        List<Grade> gradeList = gradeRepository.findGradeJoinNameOfStudent("wonchul");

        // then
        assertThat(gradeList).size().isEqualTo(1);
        assertThat(gradeList).element(0).satisfies(g -> {
            assertThat(g.getGradeNum()).isEqualTo(1);
            assertThat(g.getGradeName()).isEqualTo("일학년");
        });
    }

    @Test
	void test_findGradeSubQueryNameOfStudent() {
        // given

        // when
        List<Grade> gradeList = gradeRepository.findGradeSubQueryNameOfStudent("wonchul");

        // then
        assertThat(gradeList).size().isEqualTo(1);
        assertThat(gradeList).element(0).satisfies(g -> {
            assertThat(g.getGradeNum()).isEqualTo(1);
            assertThat(g.getGradeName()).isEqualTo("일학년");
        });
    }

    @AfterEach
	void after_clear() {
        studentRepository.deleteAll();
        gradeRepository.deleteAll();
    }
}
