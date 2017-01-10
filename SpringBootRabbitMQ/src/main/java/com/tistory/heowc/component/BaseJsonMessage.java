package com.tistory.heowc.component;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.tistory.heowc.config.RabbitMQConfig;
import com.tistory.heowc.domain.Base;

@Component
public class BaseJsonMessage {

	private static final Logger logger = Logger.getLogger(BaseJsonMessage.class);
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void onMessage(Base base) {
		logger.info("Received < " + base.toString() + " >");
	}
}
