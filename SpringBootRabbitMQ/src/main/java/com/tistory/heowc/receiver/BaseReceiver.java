package com.tistory.heowc.receiver;

import com.tistory.heowc.common.Base;
import com.tistory.heowc.common.Constat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BaseReceiver {

	private static final Logger logger = LoggerFactory.getLogger(BaseReceiver.class);

	@RabbitListener(queues = Constat.QUEUE_NAME)
	public void onMessage(Base base) {
		logger.info("Received < " + base.toString() + " >");
	}
}
