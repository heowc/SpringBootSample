package com.example.kotlin.web

import com.example.kotlin.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @Autowired
    private lateinit var service: TestService

    @GetMapping(value = ["/noAop"])
    fun noAop(): String {
        return service.test()
    }

    @GetMapping(value = ["/aop"])
    fun aop(): String {
        return service.testAop()
    }
}
