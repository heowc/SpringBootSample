package com.example.config.security;

import com.example.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private SecurityHandler securityHandler;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String LOGIN_ENTRY_POINT = "/login";
	private static final String LOGOUT_ENTRY_POINT = "/logout";

	private static final String ALL_ENTRY_POINT = "/**";

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RequestBodyToJsonFilter requestBodyToJsonFilter() throws Exception {
		RequestBodyToJsonFilter requestBodyToJsonFilter = new RequestBodyToJsonFilter(requestMatcher() ,objectMapper);
		requestBodyToJsonFilter.setAuthenticationManager(this.authenticationManager());
		requestBodyToJsonFilter.setAuthenticationSuccessHandler(securityHandler);
		return requestBodyToJsonFilter;
	}

	private RequestMatcher requestMatcher() {
		return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT).anonymous()
				.antMatchers(HttpMethod.GET, LOGOUT_ENTRY_POINT).anonymous()
				.antMatchers(ALL_ENTRY_POINT).authenticated()
			.and()
				.addFilterBefore(requestBodyToJsonFilter(), UsernamePasswordAuthenticationFilter.class)
//			.formLogin()
//				.usernameParameter("id")
//				.passwordParameter("password")
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/user")
//			.and()
			.logout()
				.logoutUrl(LOGOUT_ENTRY_POINT)
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
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