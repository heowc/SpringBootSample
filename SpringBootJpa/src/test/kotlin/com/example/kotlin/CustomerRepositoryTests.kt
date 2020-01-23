package com.example.kotlin

import com.example.kotlin.simple.domain.Customer
import com.example.kotlin.simple.repository.CustomerRepository
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CustomerRepositoryTests {

    @Autowired
    lateinit var repository: CustomerRepository

    @Test
    fun test_update() {
        val customer = repository.save(Customer(name = "heo won chul", tel = "010-xxxx-xxxx", bigo = "developer"))  // 비영속성 데이터
        customer.changeBigo("Developer")

        assertNotEquals(repository.save(customer).bigo, "developer")
    }
}