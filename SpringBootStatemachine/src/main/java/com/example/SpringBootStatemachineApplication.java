package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringBootStatemachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStatemachineApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(StateMachine<OrderState, OrderEvent> stateMachine) {
        return args -> {
            stateMachine.sendEvent(Mono.just(new GenericMessage<>(OrderEvent.OFFER_RECEIPT))).blockLast();
            stateMachine.sendEvent(Mono.just(new GenericMessage<>(OrderEvent.PURCHASE))).blockLast();
            stateMachine.sendEvent(Mono.just(new GenericMessage<>(OrderEvent.TAKEOVER))).blockLast();
            stateMachine.sendEvent(Mono.just(new GenericMessage<>(OrderEvent.CONFIRM))).blockLast();

            stateMachine.sendEvent(Mono.just(new GenericMessage<>(OrderEvent.OUT_OF_STOCK))).blockLast(); // not triggered
        };
    }
}
