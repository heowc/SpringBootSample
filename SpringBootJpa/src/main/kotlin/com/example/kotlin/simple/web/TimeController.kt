package com.example.kotlin.simple.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDateTime

@RestController
@RequestMapping("date")
class TimeController {

    val localDateTime: LocalDateTime

    @GetMapping
    get() = LocalDateTime.now()
}
