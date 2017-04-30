package com.tistory.heowc.web;

import com.tistory.heowc.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

    @GetMapping("{idx}")
    public Mono<Message> findOne(@PathVariable Long idx) {
        return Mono.just(new Message(idx, "Hello, Spring Web Flux " + idx));
    }
}