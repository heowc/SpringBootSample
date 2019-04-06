package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class SpringBootCustomJacksonApplication

fun main(args: Array<String>) {

    val context = runApplication<SpringBootCustomJacksonApplication>(*args)

    println("Let's inspect the beans provided by Spring Boot:")

    context.beanDefinitionNames.asList().sorted().forEach { println(it) }
}
