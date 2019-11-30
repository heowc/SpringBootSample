package com.example.java;

import com.example.java.simple.domain.Customer;
import com.example.java.simple.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    void test_update() {
        Customer customer = repository.save(new Customer("heo won chul", "010-xxxx-xxxx", "developer")); // 비영속성 데이터
        customer.changeBigo("Developer");

        assertNotEquals("developer", repository.save(customer).getBigo());
    }
}