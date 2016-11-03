package com.example.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.message.HelloMessage;

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
		message.setSendDate(new Date());
		return message;
	}
}


