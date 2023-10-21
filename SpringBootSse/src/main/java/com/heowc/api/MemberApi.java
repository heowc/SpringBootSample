package com.heowc.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.heowc.domain.MemberRepository;

@RestController
@RequestMapping("/members")
public class MemberApi {

    private static final Logger logger = LoggerFactory.getLogger(MemberApi.class);

    private final MemberRepository repository;
    private final PlatformTransactionManager transactionManager;

    public MemberApi(MemberRepository repository,
                     PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }

    @GetMapping("/stream-all")
    public ResponseBodyEmitter streamAll(LocalDateTime createdAt) {
        logger.info("stream all");
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter(60 * 1000L);
        Executors.newSingleThreadExecutor().execute(() -> {
            new TransactionTemplate(transactionManager).executeWithoutResult(status -> {
                repository.streamByCreatedAtAfter(Objects.requireNonNullElse(createdAt,
                                                                             LocalDateTime.now().minusDays(1)))
                          .forEach(it -> {
                              try {
                                  logger.info("send sse-data. data={}", it);
                                  emitter.send(SseEmitter.event()
                                                         .id(String.valueOf(it.getId()))
                                                         .data(it, MediaType.APPLICATION_JSON)
                                                         .build());
                                  Thread.sleep(100L);
                              } catch (Exception e) {
                                  emitter.completeWithError(e);
                              }
                          });
            });
            emitter.complete();
        });
        return emitter;
    }
}
