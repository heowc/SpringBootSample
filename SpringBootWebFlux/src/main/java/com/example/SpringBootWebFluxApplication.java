package com.example;

import com.example.domain.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringBootWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebFluxApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080/");
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Command Line Runner!!");
			webClient()
					.get()
					.uri("/message/{id}", 1L)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.exchange()
					.flatMap(cr -> cr.bodyToMono(Message.class))
					.subscribe(System.out::println);
        };
	}
}
