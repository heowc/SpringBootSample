package com.tistory.heowc;

import com.tistory.heowc.domain.Person;
import com.tistory.heowc.domain.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@Slf4j
class PersonRepositoryTest {

    private static final String KEY = "1";

    @Autowired
    private PersonRepository repository;

    @Test
    void test1_set() {
        repository.save(new Person(KEY, "wonchul", "heo"));
    }

    @Test
    void test2_get() {
        log.info(
                String.format("pop [ %s ]", repository.findById(KEY))
        );

        repository.deleteById(KEY);
    }
}
