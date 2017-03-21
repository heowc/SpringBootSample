package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

	private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);
	
	@BeforeClass
	public static void onBeforeClass(){
		logger.info("================= onBeforeClass()");
	}
	
	@AfterClass
	public static void onAfterClass(){
		logger.info("================= onAfterClass()");
	}

	@Before
	public void onBefore() {
		logger.info("================= onBefore()");
	}
	
	@After
	public void onAfter() {
		logger.info("================= onAfter()");
	}
	
	@Test
	public void test1() throws Exception {
		logger.info("================= test1()");
		assertThat(add(0, 0)).isEqualTo(0);
		
//		assertThat(33).as("check %s's age", "Won Chul").isEqualTo(33);
	}
	
	@Test
	public void test2() throws Exception {
		logger.info("================= test2()");
		assertThat(add(1, 2)).isEqualTo(3);
	}
	
	public double add(double num1, double num2) {
		return num1 + num2;
	}
}
