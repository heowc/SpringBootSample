package com.example.kotlin.simple.repository

import com.example.kotlin.simple.domain.Customer

interface CustomJpqlRepository {

    fun findByName(name: String): List<Customer>
}
