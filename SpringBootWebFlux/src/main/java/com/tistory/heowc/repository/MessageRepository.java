package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    CompletableFuture<Message> getByIdx(Long idx);
}
