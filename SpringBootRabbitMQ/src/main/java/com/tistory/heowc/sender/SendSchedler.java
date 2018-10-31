package com.tistory.heowc.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.common.Base;
import com.tistory.heowc.common.Constat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.stream.LongStream;

@Component
public class SendSchedler {

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(SendSchedler.class);

	public SendSchedler(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.mapper = mapper;
	}

	@Scheduled(cron = "0/3 * * * * *")
	public void onSend() {
		logger.info("Sending message... Start");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		LongStream.range(1, 15000)
			.parallel()
			.mapToObj(i -> Base.of(i, "Hello, RabbitMQ!"))
			.forEach(message -> rabbitTemplate.convertAndSend(Constat.QUEUE_NAME, message));

		stopWatch.stop();
		logger.info(stopWatch.toString());
		logger.info("Sending message... End");
	}
}
