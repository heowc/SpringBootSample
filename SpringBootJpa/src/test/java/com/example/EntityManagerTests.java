package com.example;

import com.example.simple.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityManagerTests {

	@Autowired
	TestEntityManager testEntityManager;

	// 비영속성 데이터
	private Customer getPersistenceContextCustomer() {
		return new Customer("heo won chul", "010-xxxx-xxxx", "developer");
	}

	// 준영속성 데이터
	private Customer getNotPersistenceContextCustomer() {
		return new Customer("heo won chul", "010-xxxx-xxxx", "developer");
	}

	@Transactional
	@Test
	public void test_insertA() {
//        org.hibernate.PersistentObjectException: detached entity passed to persist
//        testEntityManager.persist(getNotPersistenceContextCustomer());
		testEntityManager.persist(getPersistenceContextCustomer());
		testEntityManager.flush();
	}

	@Transactional
	@Test
	public void test_insertB() {
		testEntityManager.merge(getNotPersistenceContextCustomer());
		testEntityManager.flush();
	}

	@Transactional
	@Test
	public void test_insertClearAndFindAndUpdateClear() {
		testEntityManager.merge(getNotPersistenceContextCustomer()); // Persistence Context 추가
		testEntityManager.flush(); // Database 동기화
//		testEntityManager.clear(); // Persistence Context 초기화

		Customer customer = testEntityManager.find(Customer.class, 1L);
		System.out.println(customer);
		customer.changeBigo("Developer");
		testEntityManager.merge(customer); // Persistence Context 추가
		testEntityManager.flush(); // Database 동기화
//		testEntityManager.clear(); // Persistence Context 초기화

		Customer result = testEntityManager.find(Customer.class, 1L);
		System.out.println(result);
	}
}
