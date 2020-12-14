package com.tistory.heowc.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.service.ArticleService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.jackson.DatabindCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_ARTICLES = "get.articles.all";

    private static final Logger logger = LoggerFactory.getLogger(ArticleRecipientVerticle.class);
    private static final ObjectMapper mapper = DatabindCodec.mapper();

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
                logger.error("Failed to serialize result");
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