package com.tistory.heowc.service;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Transactional
@Async
@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public CompletableFuture<Message> findByOne(Long idx) {
		return CompletableFuture.completedFuture(messageRepository.findById(idx).orElse(null));
	}

	public CompletableFuture<Message> add(Message message) {
		return CompletableFuture.completedFuture(messageRepository.save(message));
	}

	public CompletableFuture<Message> modify(Message message) {
		return CompletableFuture.completedFuture(messageRepository.findById(message.getIdx()).orElseThrow(RuntimeException::new))
				.thenApply(m -> {
					m.setContent(message.getContent());
					return m;
				});
	}

	public void remove(Long idx) {
		messageRepository.deleteById(idx);
	}
}
