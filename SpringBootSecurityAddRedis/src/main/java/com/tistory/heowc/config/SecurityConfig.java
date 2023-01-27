package com.tistory.heowc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    WebSecurityCustomizer customizer() {
        return web -> {
            web.ignoring().requestMatchers("/resources/**");
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pw")
                .defaultSuccessUrl("/user", true)
                .permitAll()
                .and()
                .csrf().disable();
        return http.build();
    }
}
