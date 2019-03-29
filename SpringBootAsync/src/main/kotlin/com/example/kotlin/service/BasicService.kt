package com.example.kotlin.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class BasicService {

    @Async
    fun onAsync() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        logger.info("onAsync")
    }

    fun onSync() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        logger.info("onSync")
    }

    companion object {

        private val logger = LoggerFactory.getLogger(BasicService::class.java)
    }
}
