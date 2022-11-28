package com.example.kotlin.simple.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class TimeData(
        @Id @GeneratedValue val idx: Long? = null,
        val date: LocalDateTime)
