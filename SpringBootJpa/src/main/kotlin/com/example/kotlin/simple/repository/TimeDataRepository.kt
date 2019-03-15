package com.example.kotlin.simple.repository

import com.example.kotlin.simple.domain.TimeData
import org.springframework.data.jpa.repository.JpaRepository

interface TimeDataRepository : JpaRepository<TimeData, Long>
