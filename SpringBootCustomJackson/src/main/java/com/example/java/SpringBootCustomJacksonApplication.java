package com.example.java;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCustomJacksonApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootCustomJacksonApplication.class, args);
		
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
	}
}
