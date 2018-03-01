package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

	private static final Logger logger = LoggerFactory.getLogger(BasicService.class);

	@Async
	public void onAsync() {
		try {
			Thread.sleep(1000);
			logger.info("onAsync");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onSync() {
		try {
			Thread.sleep(1000);
			logger.info("onSync");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
