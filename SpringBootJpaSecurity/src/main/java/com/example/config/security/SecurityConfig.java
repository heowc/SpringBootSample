package com.example.config.security;

import com.example.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsServiceImpl userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").anonymous()
				.antMatchers("/**").authenticated()
			.and()
			.formLogin()
				.usernameParameter("id")
				.passwordParameter("password")
				.loginProcessingUrl("/custom-login")
				.defaultSuccessUrl("/user")
			.and()
			.logout()
				.logoutUrl("/custom-logout")
				.logoutSuccessUrl("/")
			.and()
			.csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		[ in-memory 인증 ]
//		auth.inMemoryAuthentication()
//			.withUser("heowc").password("1234").roles("USER");

//		[ 별도의 service 인증 ]
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}