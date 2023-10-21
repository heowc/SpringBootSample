package com.heowc.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.Executors;

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

    private final MemberRepository repository;
    private final PlatformTransactionManager transactionManager;

    public MemberApi(MemberRepository repository,
                     PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }

    @GetMapping("/stream-all")
    public ResponseBodyEmitter streamAll(LocalDateTime createdAt) {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter(60 * 1000L);
        Executors.newSingleThreadExecutor().execute(() -> {
            new TransactionTemplate(transactionManager).executeWithoutResult(status -> {
                repository.streamByCreatedAtAfter(Objects.requireNonNullElse(createdAt,
                                                                             LocalDateTime.now().minusDays(1)))
                          .forEach(it -> {
                              try {
                                  emitter.send(SseEmitter.event()
                                                         .data(it, MediaType.APPLICATION_JSON)
                                                         .build());
                              } catch (IOException e) {
                                  emitter.completeWithError(e);
                              }
                          });
            });
            emitter.complete();
        });
        return emitter;
    }
}
