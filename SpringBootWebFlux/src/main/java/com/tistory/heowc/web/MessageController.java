package com.tistory.heowc.web;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired MessageRepository messageRepository;

    @GetMapping("{idx}")
    public Mono<Message> findBy(@PathVariable Long idx) {
        return Mono.fromCompletionStage(messageRepository.findByIdx(idx));
    }

    @PostMapping
    public void create(@RequestBody Mono<Message> message) {
        message.subscribe(entity -> messageRepository.save(entity));
    }

    @DeleteMapping("{idx}")
    public void deleteByIdx(@PathVariable Long idx) {
        messageRepository.deleteById(idx);
    }
}