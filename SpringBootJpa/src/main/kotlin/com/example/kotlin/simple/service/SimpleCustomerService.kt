package com.example.kotlin.simple.service

import com.example.kotlin.simple.domain.Customer
import com.example.kotlin.simple.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier

@Service
@Transactional
class SimpleCustomerService : CustomService {

    @Autowired
    private val repository: CustomerRepository? = null

    override fun upsert(customer: Customer): Customer {
        return repository!!.save(customer)
    }

    override fun find(idx: Long?): Customer {
        return repository!!.findById(idx!!).orElseThrow<RuntimeException> { RuntimeException() }
    }

    override fun findByName(name: String): List<Customer> {
        return repository!!.findByName(name)
    }

    override fun delete(customer: Customer): Customer {
        repository!!.delete(customer)
        return customer
    }
}
