package com.example;

import com.example.domain.Customer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityManagerTests {

    @PersistenceContext EntityManager em;

    // 비영속성 데이터
    private Customer getPersistenceContextCustomer() {
        return new Customer("heo won chul", "010-xxxx-xxxx", "developer");
    }

    // 준영속성 데이터
    private Customer getNotPersistenceContextCustomer() {
        return new Customer(10L,"heo won chul", "010-xxxx-xxxx", "developer");
    }

    @Transactional
    @Test
    public void test_insertA() {
//        org.hibernate.PersistentObjectException: detached entity passed to persist
//        em.persist(getNotPersistenceContextCustomer());
        em.persist(getPersistenceContextCustomer());
        em.flush();
    }

    @Transactional
    @Test
    public void test_insertB() {
        em.merge(getNotPersistenceContextCustomer());
        em.flush();
    }

    @Transactional
    @Test
    public void test_insertClearAndFindAndUpdateClear() {
        em.merge(getNotPersistenceContextCustomer()); // Persistence Context 추가
        em.flush(); // Database 동기화
        em.clear(); // Persistence Context 초기화

        Customer customer = em.find(Customer.class, 10L);
        System.out.println(customer);
        customer.setBigo("Developer");
        em.merge(customer); // Persistence Context 추가
        em.flush(); // Database 동기화
        em.clear(); // Persistence Context 초기화

        Customer result = em.find(Customer.class, 10L);
        System.out.println(result);
    }

    @Transactional
    @Test
    public void test_insertAndFindAndUpdate() {
        em.merge(getNotPersistenceContextCustomer()); // Persistence Context 추가
        em.flush(); // Database 동기화
//        em.clear(); // Persistence Context 초기화

        Customer customer = em.find(Customer.class, 10L);
        System.out.println(customer);
        customer.setBigo("Developer");
        em.merge(customer); // Persistence Context 추가
        em.flush(); // Database 동기화
//        em.clear(); // Persistence Context 초기화

        Customer result = em.find(Customer.class, 10L);
        System.out.println(result);
    }
}
