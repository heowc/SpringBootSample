package com.tistory.heowc.verticle;

import com.tistory.heowc.config.AppConfig;
import com.tistory.heowc.config.loader.RouterLoader;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerVerticle extends AbstractVerticle {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RouterLoader routerLoader;

    @Override
    public void start() throws Exception {
        super.start();

        Router router = routerLoader.load();
        vertx.createHttpServer().requestHandler(router::handle).listen(appConfig.httpPort());
    }
}