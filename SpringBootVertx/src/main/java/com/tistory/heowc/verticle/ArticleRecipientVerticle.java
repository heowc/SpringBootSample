package com.tistory.heowc.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.service.ArticleService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_ARTICLES = "get.articles.all";

    private static final ObjectMapper mapper = Json.mapper;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ArticleService articleService;

    @Override
    public void start() throws Exception {
        super.start();
        eventBus
                .<String>consumer(GET_ALL_ARTICLES)
                .handler(getAllArticleService(articleService));
    }

    private Handler<Message<String>> getAllArticleService(ArticleService service) {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(service.getAllArticle()));
            } catch (JsonProcessingException e) {
                log.error("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause().toString());
            }
        });
    }
}