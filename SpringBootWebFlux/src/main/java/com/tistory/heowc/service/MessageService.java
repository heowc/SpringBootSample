package com.tistory.heowc.service;

import com.tistory.heowc.domain.Message;

import java.util.concurrent.CompletableFuture;

public interface MessageService {

    CompletableFuture<Message> findByOne(Long idx);
    CompletableFuture<Message> insert(String content);
    CompletableFuture<Message> update(final Message message);
    void delete(Long idx);
}
