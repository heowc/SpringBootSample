package com.tistory.heowc.web;

import com.tistory.heowc.domain.Message;
import com.tistory.heowc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("{idx}")
	public Mono<Message> findByOne(@PathVariable Long idx) {
		return Mono.fromCompletionStage(messageService.findByOne(idx));
	}

	@PostMapping
	public Mono<Message> add(@RequestBody Message message) {
		return Mono.fromCompletionStage(messageService.add(message));
	}

	@PutMapping
	public Mono<Message> modify(@RequestBody Message message) {
		return Mono.fromCompletionStage(messageService.modify(message));
	}

	@DeleteMapping("{idx}")
	public void remove(@PathVariable Long idx) {
		messageService.remove(idx);
	}
}