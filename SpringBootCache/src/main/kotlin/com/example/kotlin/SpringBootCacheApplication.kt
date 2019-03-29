package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpringBootCacheApplication

fun main(args: Array<String>) {
    runApplication<SpringBootCacheApplication>(*args)
}