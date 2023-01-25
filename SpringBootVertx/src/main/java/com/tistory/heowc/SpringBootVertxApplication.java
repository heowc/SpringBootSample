package com.tistory.heowc;

import com.tistory.heowc.verticle.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootVertxApplication {

    @Autowired
    private ServerVerticle serverVerticle;

//    @Autowired
//    private ArticleRecipientVerticle articleRecipientVerticle;

    @Autowired
    private Vertx vertx;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVertxApplication.class, args);
    }

    @PostConstruct
    public void create() {
        vertx.deployVerticle(serverVerticle);
//        vertx.deployVerticle(articleRecipientVerticle);
    }
}
