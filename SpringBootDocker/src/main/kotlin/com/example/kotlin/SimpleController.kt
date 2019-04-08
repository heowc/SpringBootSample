package com.example.kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SimpleController {

    @GetMapping
    fun message(): String {
        return "Hello Jib With Kotlin!!"
    }
}
