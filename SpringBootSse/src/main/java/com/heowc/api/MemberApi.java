package com.heowc.api;

import com.heowc.domain.MemberRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
public class MemberApi {

    private final MemberRepository repository;

    public MemberApi(MemberRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @GetMapping("/stream-all")
    public SseEmitter streamAll(LocalDateTime createdAt) {
        final SseEmitter emitter = new SseEmitter();
        repository.findAllByCreatedAtAfter(createdAt)
                  .forEach(it -> {
                      try {
                          emitter.send(SseEmitter.event()
                                                 .id(String.valueOf(it.getId()))
                                                 .data(it, MediaType.APPLICATION_JSON)
                                                 .build());
                      } catch (IOException e) {
                          emitter.completeWithError(e);
                      }
                  });
        return emitter;
    }
}
