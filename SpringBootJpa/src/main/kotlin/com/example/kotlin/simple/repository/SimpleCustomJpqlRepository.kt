package com.example.kotlin.simple.repository

import com.example.kotlin.simple.domain.Customer
import jakarta.persistence.EntityManager

class SimpleCustomJpqlRepository(val em: EntityManager) : CustomJpqlRepository {

    override fun findByName(name: String): List<Customer> {
        return em.createNamedQuery("Custom.findByName", Customer::class.java)
                .setParameter("name", name)
                .resultList
    }
}
