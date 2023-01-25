package com.example.config.security;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RequestMatcherDelegatingAuthenticationManagerResolver;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String LOGOUT_ENTRY_POINT = "/logout";

    private static final String ALL_ENTRY_POINT = "/**";

    AuthenticationManagerResolver<HttpServletRequest> resolver() {
        return RequestMatcherDelegatingAuthenticationManagerResolver.builder()
                .add(new AntPathRequestMatcher(ALL_ENTRY_POINT), new ProviderManager())
                .build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT).permitAll()
                .requestMatchers(HttpMethod.GET, LOGOUT_ENTRY_POINT).permitAll()
                .requestMatchers(ALL_ENTRY_POINT).authenticated()
                .and()
                .addFilterBefore(restAuthenticationProcessingFilter(),
                                 UsernamePasswordAuthenticationFilter.class)
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
        return http.build();
    }

    @Bean
    public RestAuthenticationProcessingFilter restAuthenticationProcessingFilter() throws Exception {
        RestAuthenticationProcessingFilter restAuthenticationProcessingFilter =
                new RestAuthenticationProcessingFilter(requestMatcher());
        restAuthenticationProcessingFilter.setAuthenticationSuccessHandler(
                new RestAuthenticationSuccessHandler());
        restAuthenticationProcessingFilter.setAuthenticationManager(authentication -> {
            return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(),
                                                           authentication.getCredentials());
        });
        return restAuthenticationProcessingFilter;
    }

    private RequestMatcher requestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                               .username("user")
                               .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                               .roles("USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password(
                                        "{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                                .roles("USER", "ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}