package com.tistory.heowc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.BaseSecurityHandler;
import com.tistory.heowc.auth.ajax.AjaxAuthenticationProvider;
import com.tistory.heowc.auth.ajax.filter.AjaxAuthenticationFilter;
import com.tistory.heowc.auth.jwt.JwtAuthenticationProvider;
import com.tistory.heowc.auth.jwt.filter.JwtAuthenticationFilter;
import com.tistory.heowc.auth.jwt.matcher.SkipPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired JwtAuthenticationProvider jwtProvider;
	@Autowired AjaxAuthenticationProvider ajaxProvider;
	
	@Autowired BaseSecurityHandler securityHandler;
	
	@Autowired ObjectMapper objectMapper;
	
	public static final String LOGIN_END_POINT = "/login";
	public static final String TOKEN_END_POINT = "/token";
	public static final String ROOT_END_POINT  = "/**";
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(ajaxProvider)
			.authenticationProvider(jwtProvider);
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
				.antMatchers(TOKEN_END_POINT).permitAll()
				.antMatchers(LOGIN_END_POINT).permitAll()
				.antMatchers(ROOT_END_POINT).authenticated()
			.and()
				.formLogin();
//				.loginProcessingUrl(TOKEN_END_POINT);
	}
	
	private AntPathRequestMatcher antPathRequestMatcher() {
		return new AntPathRequestMatcher(TOKEN_END_POINT, HttpMethod.POST.name());
	}
	
	public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
		AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(securityHandler);
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}
	
	private SkipPathRequestMatcher skipPathRequestMatcher() {
		return new SkipPathRequestMatcher(Arrays.asList(LOGIN_END_POINT, TOKEN_END_POINT));
	}
	
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}
}