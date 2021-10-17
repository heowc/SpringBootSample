package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootApplication
public class SpringBootStatemachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStatemachineApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(StateMachine<OrderState, OrderEvent> stateMachine) {
        return args -> {
            final String orderId = UUID.randomUUID().toString();
            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.OFFER_RECEIPT, orderId))).blockLast();
            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.PURCHASE, orderId))).blockLast();
            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.TAKE_OVER, orderId))).blockLast();
            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.CONFIRM, orderId))).blockLast();
            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.CONFIRM, orderId))).blockLast(); // not triggered

            stateMachine.sendEvent(Mono.just(buildMessage(OrderEvent.OUT_OF_STOCK, orderId))).blockLast(); // not triggered
        };
    }

    private static Message<OrderEvent> buildMessage(OrderEvent event, String orderId) {
        return MessageBuilder.withPayload(event)
                .setHeader("orderId", orderId)
                .build();
    }
}
