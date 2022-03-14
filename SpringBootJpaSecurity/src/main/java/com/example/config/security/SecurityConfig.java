package com.example.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String LOGOUT_ENTRY_POINT = "/logout";

    private static final String ALL_ENTRY_POINT = "/**";

    @Bean
    public RestAuthenticationProcessingFilter restAuthenticationProcessingFilter() throws Exception {
        RestAuthenticationProcessingFilter restAuthenticationProcessingFilter = new RestAuthenticationProcessingFilter(requestMatcher());
        restAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager());
        restAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new RestAuthenticationSuccessHandler());
        return restAuthenticationProcessingFilter;
    }

    private RequestMatcher requestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(HttpMethod.GET, LOGOUT_ENTRY_POINT).permitAll()
                .antMatchers(ALL_ENTRY_POINT).authenticated()
                .and()
                .addFilterBefore(restAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
//			.formLogin()
//				.usernameParameter("id")
//				.passwordParameter("password")
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/user")
//			.and()
                .logout()
                .logoutUrl(LOGOUT_ENTRY_POINT)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //		[ in-memory 인증 ]
//		auth.inMemoryAuthentication()
//			.withUser("heowc").password("1234").roles("USER");

//		[ 별도의 service 인증 ]
        auth.userDetailsService(userDetailsService);
    }
}