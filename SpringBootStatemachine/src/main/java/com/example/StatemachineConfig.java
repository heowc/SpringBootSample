package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StatemachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    private final Logger logger = LoggerFactory.getLogger(StatemachineConfig.class);

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states)
            throws Exception {
        states.withStates()
                .initial(OrderState.RECEIPTING)
                .end(OrderState.COMPLETED)
                .end(OrderState.CANCEL)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                    // 접수 -> (주문 신청) -> 주문 대기
                    .guard(offerReceiptGuard())
                    .source(OrderState.RECEIPTING).target(OrderState.ORDER_PENDING)
                    .event(OrderEvent.OFFER_RECEIPT)
                    .action(offerReceiptAction())
                .and()
                .withExternal()
                    // 주문 대기 -> (구매) -> 배송 대기
                    .source(OrderState.ORDER_PENDING).target(OrderState.DELIVERY_PENDING)
                    .event(OrderEvent.PURCHASE)
                .and()
                .withExternal()
                    // 배송 대기 -> (인계) -> 배송 중
                    .source(OrderState.DELIVERY_PENDING).target(OrderState.DELIVERY_PROCEEDING)
                    .event(OrderEvent.TAKE_OVER)
                .and()
                .withExternal()
                    // 배송 중 -> (확정) -> 완료
                    .source(OrderState.DELIVERY_PROCEEDING).target(OrderState.COMPLETED)
                    .event(OrderEvent.CONFIRM);
    }

    @Bean
    Action<OrderState, OrderEvent> offerReceiptAction() {
        return context -> logger.info("offer receipt action. {}", context);
    }

    @Bean
    Guard<OrderState, OrderEvent> offerReceiptGuard() {
        return context -> true;
    }


    @Bean
    StateMachineListener<OrderState, OrderEvent> listener() {

        return new StateMachineListenerAdapter<OrderState, OrderEvent>() {

            private final Logger logger = LoggerFactory.getLogger(StateMachineListener.class);

            @Override
            public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                if (from == null) {
                    logger.info("Starting state to [{}]", to.getId());
                } else {
                    logger.info("Changing state from [{}] to [{}]", to.getId(), from.getId());
                }
            }
        };
    }
}
