package com.example.kotlin.component

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class SimpleAspect {

    private val logger = LoggerFactory.getLogger(SimpleAspect::class.java)

    @Before("execution(* com.example.kotlin.service.*.*Aop(..))")
    fun onBeforeHandler(joinPoint: JoinPoint) {
        logger.info("=============== onBeforeThing")
    }

    @After("execution(* com.example.kotlin.service.*.*Aop(..))")
    fun onAfterHandler(joinPoint: JoinPoint) {
        logger.info("=============== onAfterHandler")
    }

    @AfterReturning(pointcut = "execution(* com.example.kotlin.service.*.*Aop(..))", returning = "str")
    fun onAfterReturningHandler(joinPoint: JoinPoint, str: Any) {
        logger.info("@AfterReturning : $str")
        logger.info("=============== onAfterReturningHandler")
    }

    @Pointcut("execution(* com.example.kotlin.service.*.*Aop(..))")
    fun onPointcut(joinPoint: JoinPoint) {
        logger.info("=============== onPointcut")
    }
}
