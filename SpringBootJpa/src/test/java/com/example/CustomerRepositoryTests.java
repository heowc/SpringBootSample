package com.example;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRepositoryTests {

    @Autowired CustomerRepository repository;
    @PersistenceContext EntityManager entityManager;

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
        entityManager.flush();
    }

    @Test
    public void test_update() {
        Customer customer = repository.save(getPersistenceContextCustomer());
        customer.setBigo("Developer");

        assertNotEquals(repository.save(customer), getNotPersistenceContextCustomer());
        entityManager.flush();
    }

    @Test
    public void test_select() {
        assertNull(repository.findOne(1L));
    }

    @Test
    public void test_delete() {
//        repository.delete(1L);
    }
}