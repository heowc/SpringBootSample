package com.example.kotlin

import com.example.kotlin.simple.domain.Customer
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
open class EntityManagerTests {

    @Autowired
    lateinit var testEntityManager: TestEntityManager

    @Test
    fun test_insertClearAndFindAndUpdateClear() {
        val customer = testEntityManager.persistFlushFind(com.example.kotlin.simple.domain.Customer(name = "heo won chul", tel = "010-xxxx-xxxx", bigo = "developer")) // 비영속성 데이터
        customer.changeBigo("Developer")
        testEntityManager.flush() // Database 동기화
        //		  testEntityManager.clear(); // Persistence Context 초기화

        val result = testEntityManager.find(Customer::class.java, customer.idx)
        assertNotEquals(result.bigo, "developer")
    }
}
