package com.example.kotlin.simple.repository

import com.example.kotlin.simple.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>, CustomJpqlRepository




