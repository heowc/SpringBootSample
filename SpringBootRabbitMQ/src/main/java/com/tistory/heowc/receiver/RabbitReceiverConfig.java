package com.tistory.heowc.receiver;

import com.tistory.heowc.common.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitReceiverConfig {

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable(Constant.QUEUE_NAME).build();
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(Constant.TOPIC_EXCHANGE_NAME).build();
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(Constant.QUEUE_NAME);
    }
}
