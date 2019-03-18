package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

	private static final Logger logger = LoggerFactory.getLogger(BasicService.class);

	@Async
	public void onAsync() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("onAsync");
	}

	public void onSync() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("onSync");
	}
}
