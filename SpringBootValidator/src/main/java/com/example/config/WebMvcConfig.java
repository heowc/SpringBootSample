package com.example.config;

import org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // See AbstractApplicationContext#initMessageSource
    @Component("messageSource")
    static class ValidationMessageSource extends ResourceBundleMessageSource {

        ValidationMessageSource() {
            setBasename(AbstractMessageInterpolator.USER_VALIDATION_MESSAGES);
            setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        }
    }
}
