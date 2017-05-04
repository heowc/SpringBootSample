package com.tistory.heowc.web;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired MessageRepository repository;

//    @GetMapping("{idx}")
//    public Mono<Message> findOne(@PathVariable Long idx) {
//        return Mono.just(new Message(idx, "Hello, Spring Web Flux " + idx));
//    }

    @GetMapping("{idx}")
    public Mono<Message> findOne(@PathVariable Long idx) {
        return Mono.fromFuture(repository.getByIdx(idx));
    }

    @PostMapping
    public Mono<Message> create(@RequestBody Message message) {
        return Mono.just(repository.save(message));
    }
}