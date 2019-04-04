package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCorsApplication

fun main(args: Array<String>) {
    runApplication<SpringBootCorsApplication>(*args)
}