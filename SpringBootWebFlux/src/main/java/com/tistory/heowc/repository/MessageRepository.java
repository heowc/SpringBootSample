package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Async
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    CompletableFuture<Message> findByIdx(Long idx);
}
