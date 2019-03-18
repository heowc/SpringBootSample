package com.example.kotlin.onetomany.repository

import com.example.kotlin.onetomany.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByName(name: String): Product
}