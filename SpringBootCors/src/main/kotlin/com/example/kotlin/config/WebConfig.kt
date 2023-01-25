package com.example.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig {

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/message/**")
                        .allowedOrigins("*")
                        .allowedMethods(HttpMethod.POST.name())
                        .allowCredentials(false)
                        .maxAge(3600)
            }
        }
    }

//    => webflux 사용시 작성법
//
//    @Bean
//    fun webFluxConfigurer(): WebFluxConfigurer {
//        return object : WebFluxConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/message/**")
//                        .allowedOrigins("*")
//                        .allowedMethods(HttpMethod.POST.name)
//                        .allowCredentials(false)
//                        .maxAge(3600)
//            }
//        }
//    }
}