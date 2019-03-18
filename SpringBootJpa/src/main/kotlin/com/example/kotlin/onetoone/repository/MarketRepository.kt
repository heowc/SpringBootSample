package com.example.kotlin.onetoone.repository

import com.example.kotlin.onetoone.domain.Market
import org.springframework.data.jpa.repository.JpaRepository

interface MarketRepository : JpaRepository<Market, Long>
