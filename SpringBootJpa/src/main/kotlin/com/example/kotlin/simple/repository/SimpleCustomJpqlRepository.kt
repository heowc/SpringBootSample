package com.example.kotlin.simple.repository

import com.example.kotlin.simple.domain.Customer

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class SimpleCustomJpqlRepository : CustomJpqlRepository {

    @PersistenceContext
    private val em: EntityManager? = null

    override fun findByName(name: String): List<Customer> {
        return em!!.createNamedQuery("Custom.findByName", Customer::class.java)
                .setParameter("name", name)
                .resultList
    }
}
