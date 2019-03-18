package com.example.kotlin.onetomany.repository

import com.example.kotlin.onetomany.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
