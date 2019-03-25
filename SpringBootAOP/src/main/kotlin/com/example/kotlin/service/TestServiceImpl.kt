package com.example.kotlin.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TestServiceImpl : TestService {

    private val logger = LoggerFactory.getLogger(TestServiceImpl::class.java)

    override fun test(): String {
        val msg = "Hello, Spring Boot With Kotlin No AOP"
        logger.info(msg)
        return msg
    }

    override fun testAop(): String {
        val msg = "Hello, Spring Boot With Kotlin AOP"
        logger.info(msg)
        return msg
    }
}
