package com.tistory.heowc.router;

import com.tistory.heowc.service.ArticleService;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRouter {

    @Autowired
    private Router router;

    @Autowired
    private ArticleService service;

    public Route route() {
        return router.route("/article")
                .method(HttpMethod.GET).handler(getArticleAllHandler());
    }

    private Handler<RoutingContext> getArticleAllHandler() {
        return rc -> {
            rc.response().putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            rc.response().end(Json.encode(service.getAllArticle()));
        };
    }

//    @Autowired
//    private EventBus eventBus;

//    private void getAllArticlesHandler(RoutingContext routingContext) {
//        eventBus
//                .<String>send(ArticleRecipientVerticle.GET_ALL_ARTICLES, "", response -> {
//                    if (response.succeeded()) {
//                        routingContext.response()
//                                .putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
//                                .setStatusCode(HttpStatus.OK.value())
//                                .end(response.result().body());
//                    } else {
//                        routingContext.response()
//                                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                                .end();
//                    }
//                });
//    }
}
