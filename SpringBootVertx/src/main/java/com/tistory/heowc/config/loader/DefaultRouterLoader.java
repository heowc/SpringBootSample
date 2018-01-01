package com.tistory.heowc.config.loader;

import com.tistory.heowc.router.ArticleRouter;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRouterLoader implements RouterLoader {

    @Autowired
    private Router router;

    @Autowired
    private ArticleRouter articleRouter;

    public Router load() {
        articleRouter.route();
        return router;
    }
}
