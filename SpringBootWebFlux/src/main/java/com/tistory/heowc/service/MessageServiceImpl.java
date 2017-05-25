package com.tistory.heowc.service;


import com.tistory.heowc.domain.Message;
import com.tistory.heowc.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@Async
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public CompletableFuture<Message> findByOne(Long idx) {
        return CompletableFuture.completedFuture(messageRepository.findById(idx).get());
    }

    @Override
    public CompletableFuture<Message> insert(String content) {
        return CompletableFuture.completedFuture(messageRepository.save(new Message(content)));
    }

    @Override
    public CompletableFuture<Message> update(final Message message) {
        return CompletableFuture.completedFuture(messageRepository.findById(message.getIdx()).get())
                                .thenApply(msg -> {
                                    msg.setContent(message.getContent());
                                    return msg;
                                });
    }

    @Override
    public void delete(Long idx) {
        messageRepository.deleteById(idx);
    }
}
