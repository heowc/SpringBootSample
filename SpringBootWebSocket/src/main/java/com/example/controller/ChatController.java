package com.example.controller;

import com.example.message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

	@MessageMapping("hello")
	@SendTo("/chat/hello")
	public HelloMessage hello(HelloMessage message) throws Exception {
		Thread.sleep(100);
		return message;
	}

	@MessageMapping("bye")
	@SendTo("/chat/bye")
	public HelloMessage bye(HelloMessage message) throws Exception {
		Thread.sleep(100);
		return message;
	}

	@MessageMapping("detail")
	@SendTo("/chat/detail")
	public HelloMessage detail(HelloMessage message) throws Exception {
		Thread.sleep(100);
		message.setSendDate(LocalDateTime.now());
		return message;
	}
}


