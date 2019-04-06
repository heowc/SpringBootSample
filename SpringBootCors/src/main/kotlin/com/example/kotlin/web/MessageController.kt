package com.example.kotlin.web

import com.example.kotlin.domain.Message
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("message")
class MessageController {

    //@CrossOrigin
    @GetMapping("{value}")
    fun get(@PathVariable value: String): String {
        return value
    }

    @PostMapping
    fun post(@RequestBody message: Message): Message {
        return message
    }
}
