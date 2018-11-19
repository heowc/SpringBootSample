package com.tistory.heowc.receiver;

import com.tistory.heowc.common.Message;
import com.tistory.heowc.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

	@RabbitListener(queues = Constant.QUEUE_NAME)
	public void onMessage(Message message) {
		logger.info("Received < " + message.toString() + " >");
	}
}
