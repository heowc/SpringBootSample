package com.example;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class SpringBootOath2Application extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOath2Application.class, args);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions().disable()
			.and()
			.authorizeRequests()
				.anyRequest().authenticated();
	}
	
	@Bean
	public JdbcTokenStore tokenStore(DataSource dataSource) {
		return new JdbcTokenStore(dataSource);
	}
}


