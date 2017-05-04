package com.tistory.heowc;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebFluxApplication {

	@Autowired MessageRepository messageRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebFluxApplication.class, args);
	}

//	@Bean
//	public WebClient webClient() {
//		return WebClient.create("http://localhost:8080/");
//	}
//
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			messageRepository.save(new Message("hi"));
		};
	}
}
