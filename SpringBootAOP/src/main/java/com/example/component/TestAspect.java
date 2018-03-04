package com.example.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	private static final Logger logger = LoggerFactory.getLogger(TestAspect.class);

	@Before("execution(* com.example.service.*.*Aop(..))")
	public void onBeforeHandler(JoinPoint joinPoint) {
		logger.info("=============== onBeforeThing");
	}

	@After("execution(* com.example.service.*.*Aop(..))")
	public void onAfterHandler(JoinPoint joinPoint) {
		logger.info("=============== onAfterHandler");
	}

	@AfterReturning(pointcut = "execution(* com.example.service.*.*Aop(..))", returning = "str")
	public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
		logger.info("@AfterReturning : " + str);
		logger.info("=============== onAfterReturningHandler");
	}

	@Pointcut("execution(* com.example.service.*.*Aop(..))")
	public void onPointcut(JoinPoint joinPoint) {
		logger.info("=============== onPointcut");
	}
}
