package com.tistory.heowc.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MessageRouter {

	@Bean
	public RouterFunction<ServerResponse> routerFunction(MessageHandler messageHandler) {
		return route(GET("message/{id}").and(accept(MediaType.APPLICATION_JSON)), messageHandler::findByOne)
				.andRoute(POST("message").and(accept(MediaType.APPLICATION_JSON)), messageHandler::add)
				.andRoute(PUT("message").and(accept(MediaType.APPLICATION_JSON)), messageHandler::modify)
				.andRoute(DELETE("message/{id}").and(accept(MediaType.APPLICATION_JSON)), messageHandler::remove);
	}
}
