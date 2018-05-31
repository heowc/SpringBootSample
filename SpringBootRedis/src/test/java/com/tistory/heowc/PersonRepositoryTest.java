package com.tistory.heowc;

import com.tistory.heowc.domain.Person;
import com.tistory.heowc.domain.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class PersonRepositoryTest {

    private static final String KEY = "1";

    @Autowired
    private PersonRepository repository;

    @Test
    public void test1_set() {
        repository.save(new Person(KEY, "wonchul", "heo"));
    }

    @Test
    public void test2_get() {
        log.info(
                String.format("pop [ %s ]", repository.findById(KEY))
        );

        repository.deleteById(KEY);
    }
}
