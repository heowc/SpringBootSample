package com.tistory.heowc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.UserDetailsServiceImpl;
import com.tistory.heowc.auth.ajax.AjaxAuthenticationProvider;
import com.tistory.heowc.auth.ajax.filter.AjaxAuthenticationFilter;
import com.tistory.heowc.auth.jwt.JwtAuthenticationProvider;
import com.tistory.heowc.auth.jwt.JwtFactory;
import com.tistory.heowc.auth.jwt.JwtSecurityHandler;
import com.tistory.heowc.auth.jwt.filter.JwtAuthenticationFilter;
import com.tistory.heowc.auth.jwt.matcher.SkipPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtAuthenticationProvider jwtProvider;
	
	@Autowired
	AjaxAuthenticationProvider ajaxProvider;
	
	@Autowired
	JwtSecurityHandler securityHandler;
	
	@Autowired
	JwtFactory jwtFactory;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtProvider)
			.authenticationProvider(ajaxProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginProcessingUrl("/token");
	}
	
	public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
		AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(new AntPathRequestMatcher("/token", HttpMethod.POST.name()), objectMapper);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(securityHandler);
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}
	
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(new SkipPathRequestMatcher(Arrays.asList("/login", "/token"), "/login"), jwtFactory);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailsServiceImpl();
	}
}