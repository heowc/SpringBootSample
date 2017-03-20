package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired DataSource dataSource;
	
	@Autowired UserDetailsServiceImpl userDetailsService;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
/* 1 */			.antMatchers("/**")
				.authenticated()
			.and()
/* 2 */		.formLogin()
				.usernameParameter("userId")
				.passwordParameter("userPassword")
				.loginProcessingUrl("/customLogin")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
/* 3 */		.logout()
				.logoutUrl("/customLogout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
/* 4 */		.csrf().disable()
			;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//1.	auth
//			.inMemoryAuthentication()
//				.withUser("admin").password("1234").roles("ADMIN");
//2.	auth.jdbcAuthentication().dataSource(dataSource);
//3.	
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}