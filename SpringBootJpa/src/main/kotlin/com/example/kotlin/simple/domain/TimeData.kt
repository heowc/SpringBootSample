package com.example.kotlin.simple.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDateTime

@Entity
class TimeData(
        @Id @GeneratedValue val idx: Long? = null,
        val date: LocalDateTime) {

    constructor() : this(null, LocalDateTime.now())

    override fun toString(): String {
        return "TimeData{" +
                "idx=" + idx +
                ", date=" + date +
                '}'.toString()
    }
}
