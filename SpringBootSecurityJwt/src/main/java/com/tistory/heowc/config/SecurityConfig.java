package com.tistory.heowc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
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
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private AjaxAuthenticationProvider ajaxProvider;

    @Autowired
    private BaseSecurityHandler securityHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String TOKEN_ENTRY_POINT = "/token";
    private static final String ERROR_ENTRY_POINT = "/error";
    private static final String ROOT_ENTRY_POINT = "/**";

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/resources/**");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), AuthorizationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(ajaxProvider)
                .authenticationProvider(jwtProvider)
                .authorizeHttpRequests()
                .requestMatchers(ROOT_ENTRY_POINT).authenticated()
                .requestMatchers(TOKEN_ENTRY_POINT).permitAll()
                .requestMatchers(LOGIN_ENTRY_POINT).permitAll()
                .requestMatchers(ERROR_ENTRY_POINT).permitAll();
        return http.build();
    }

    @Bean
    public AntPathRequestMatcher antPathRequestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
        filter.setAuthenticationSuccessHandler(securityHandler);
        filter.setAuthenticationFailureHandler(securityHandler);
        filter.setAuthenticationManager(authentication -> {
            return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(),
                                                           authentication.getCredentials());
        });
        return filter;
    }

    @Bean
    public SkipPathRequestMatcher skipPathRequestMatcher() {
        return new SkipPathRequestMatcher(
                Arrays.asList(LOGIN_ENTRY_POINT, TOKEN_ENTRY_POINT, ERROR_ENTRY_POINT));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationFailureHandler(securityHandler);
        filter.setAuthenticationManager(authentication -> {
            return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(),
                                                           authentication.getCredentials());
        });
        return filter;
    }
}