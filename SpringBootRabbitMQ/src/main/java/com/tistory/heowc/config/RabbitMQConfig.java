package com.tistory.heowc.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tistory.heowc.component.BaseMesage;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
	
	public static final String QUEUE_NAME = "queue";
	
	private static final String EXCHANGE = QUEUE_NAME + "-exchange";
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey(QUEUE_NAME);
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
	
	@Bean
	public SimpleMessageListenerContainer container() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueueNames(QUEUE_NAME);
//		container.setMessageListener(baseMesage());
		container.setMessageConverter(jsonMessageConverter());
		return container;
	}
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
	}
	
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public BaseMesage baseMesage() {
		return new BaseMesage();
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("rabbitmq");
		factory.setPassword("rabbitmq");
		return factory;
	}
}
