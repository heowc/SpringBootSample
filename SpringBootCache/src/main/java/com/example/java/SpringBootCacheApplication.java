package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}
}
