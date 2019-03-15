package com.example.kotlin.simple.service

import com.example.kotlin.simple.domain.Customer

interface CustomService {

    fun upsert(customer: Customer): Customer

    fun find(idx: Long?): Customer

    fun findByName(name: String): List<Customer>

    fun delete(customer: Customer): Customer
}
