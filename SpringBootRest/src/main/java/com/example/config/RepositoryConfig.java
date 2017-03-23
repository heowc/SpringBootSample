package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.event.PersonEventHandler;

@Configuration
public class RepositoryConfig {

	@Bean
	PersonEventHandler personEventHandler(){
		return new PersonEventHandler();
	}
}
