package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig {

	@Bean
	public WebSocketMessageBrokerConfigurer webSocketMessageBrokerConfigurer() {
		return new WebSocketMessageBrokerConfigurer() {
			@Override
			public void registerStompEndpoints(StompEndpointRegistry registry) {
				registry.addEndpoint("/wonchul-websocket").withSockJS();
			}

			@Override
			public void configureMessageBroker(MessageBrokerRegistry registry) {
				registry.enableSimpleBroker("/chat");
				registry.setApplicationDestinationPrefixes("/");
			}
		};
	}
}