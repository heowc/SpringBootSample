package com.tistory.heowc.component;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class BaseMesage implements MessageListener {

	private static final Logger logger = Logger.getLogger(BaseMesage.class);
	
	@Override
	public void onMessage(Message message) {
		logger.info("Received < " + new String(message.getBody()) + " >");
	}
}
