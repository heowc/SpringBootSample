package com.example.kotlin.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class BasicService {

    companion object {
        private val logger = LoggerFactory.getLogger(BasicService::class.java)
    }

    @Async
    fun onAsync() {
        Thread.sleep(1000)
        logger.info("onAsync")
    }

    fun onSync() {
        Thread.sleep(1000)
        logger.info("onSync")
    }
}
