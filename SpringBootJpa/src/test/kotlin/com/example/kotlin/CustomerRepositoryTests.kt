package com.example.kotlin

import com.example.kotlin.simple.domain.Customer
import com.example.kotlin.simple.repository.CustomerRepository
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
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