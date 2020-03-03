package com.heowc.event;

import org.springframework.context.ApplicationEvent;

public class PasswordChangedEvent extends ApplicationEvent {

    private final String memberId;

    public PasswordChangedEvent(Object source, String memberId) {
        super(source);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
