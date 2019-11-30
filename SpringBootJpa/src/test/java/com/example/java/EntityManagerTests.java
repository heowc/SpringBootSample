package com.example.java;

import com.example.java.simple.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class EntityManagerTests {

    @Autowired
    private TestEntityManager testEntityManager;

    // 비영속성 데이터
    private Customer getPersistenceContextCustomer() {
        return new Customer("heo won chul", "010-xxxx-xxxx", "developer");
    }

    @Test
    void test_insertClearAndFindAndUpdateClear() {
        Customer customer = testEntityManager.persistFlushFind(getPersistenceContextCustomer());
        customer.changeBigo("Developer");
        testEntityManager.flush(); // Database 동기화
//		  testEntityManager.clear(); // Persistence Context 초기화

        Customer result = testEntityManager.find(Customer.class, customer.getIdx());
        assertNotEquals("developer", result.getBigo());
    }
}
