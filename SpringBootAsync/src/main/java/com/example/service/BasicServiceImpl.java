package com.example.service;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl implements BasicService {

	private static final Logger logger = Logger.getLogger(BasicServiceImpl.class);
	
	@Async
	@Override
	public void onAsync() {
		try {
			Thread.sleep(1000);
			logger.info("onAsync");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSync() {
		try {
			Thread.sleep(1000);
			logger.info("onSync");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
