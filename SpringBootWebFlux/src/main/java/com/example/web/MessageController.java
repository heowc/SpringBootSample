package com.example.web;

import com.example.domain.Message;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

    @GetMapping("{idx}")
    public Mono<Message> findOne(@PathVariable Long idx) {
        return Mono.just(new Message(idx, "Hello, Spring Web Flux " + idx));
    }
}
