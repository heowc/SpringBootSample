package com.tistory.heowc.config;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @Bean
    public Vertx vertx() {
        return Vertx.vertx(vertxOptions());
    }

    @Bean
    public VertxOptions vertxOptions() {
        VertxOptions vertxOptions = new VertxOptions();
        return vertxOptions;
    }

    @Bean
    public Router router() {
        return Router.router(vertx());
    }

    @Bean
    public EventBus eventBus() {
        return vertx().eventBus();
    }
}