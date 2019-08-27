package com.example.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/message/**")
                        .allowedOrigins("*")
                        .allowedMethods(HttpMethod.POST.name())
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

//  => webflux 사용시 작성법
//
//    @Bean
//    public WebFluxConfigurer webFluxConfigurer() {
//        return new WebFluxConfigurerComposite() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/message/**")
//                        .allowedOrigins("*")
//                        .allowedMethods(HttpMethod.POST.name())
//                        .allowCredentials(false)
//                        .maxAge(3600);
//            }
//        };
//    }
}