package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootJpaKotlinApplication {
    fun main(args: Array<String>) {
        runApplication<SpringBootJpaKotlinApplication>(*args)
    }
}

