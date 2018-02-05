package com.example;

import com.example.simple.domain.Customer;
import com.example.simple.repository.CustomerRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    // 비영속성 데이터
    private Customer getPersistenceContextCustomer() {
        return new Customer("heo won chul", "010-xxxx-xxxx", "developer");
    }

    // 준영속성 데이터
    private Customer getNotPersistenceContextCustomer() {
        return new Customer(1L,"heo won chul", "010-xxxx-xxxx", "developer");
    }

    @Test
    public void test_insert() {
        assertEquals(repository.save(getPersistenceContextCustomer()), getNotPersistenceContextCustomer());
        testEntityManager.flush();
    }

    @Test
    public void test_update() {
        Customer customer = repository.save(getPersistenceContextCustomer());
        customer.setBigo("Developer");

        assertNotEquals(repository.save(customer), getNotPersistenceContextCustomer());
        testEntityManager.flush();
    }

    @Test
    public void test_select() {
        assertNull(repository.findOne(1L));
    }

    @Test
    public void test_findByName() {
        assertEquals(repository.findByName(null).size(), 0);
    }

    @Test
    public void test_delete() {
//        repository.delete(1L);
    }
}