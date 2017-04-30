package com.tistory.heowc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebFluxApplication.class, args);
	}

//	@Bean
//	public WebClient webClient() {
//		return WebClient.create("http://localhost:8080/");
//	}
//
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			System.out.println("Command Line Runner!!");
//			webClient()
//					.get()
//					.uri("/message/{id}", 1L)
//					.accept(MediaType.APPLICATION_JSON_UTF8)
//					.exchange()
//					.flatMap(cr -> cr.bodyToMono(Message.class))
//					.subscribe(System.out::println);
//		};
//	}
}
