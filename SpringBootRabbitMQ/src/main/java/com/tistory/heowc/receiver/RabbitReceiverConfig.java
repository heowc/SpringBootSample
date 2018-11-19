package com.tistory.heowc.receiver;

import com.tistory.heowc.common.Constat;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitReceiverConfig {

    private static final String TOPIC_EXCHANGE_NAME = Constat.QUEUE_NAME + "-exchange";

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable(Constat.QUEUE_NAME).build();
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME).build();
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(Constat.QUEUE_NAME);
    }
}
