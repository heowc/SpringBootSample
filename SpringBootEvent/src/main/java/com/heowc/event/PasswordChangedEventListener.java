package com.heowc.event;

import com.heowc.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PasswordChangedEventListener {

    private static final String MESSAGE_FORMAT = "%s's password is changed";

    @Autowired
    private SendService sendService;

    @EventListener
    public void onChange(PasswordChangedEvent event) {
        sendService.send(String.format(MESSAGE_FORMAT, event.getMemberId()));
    }
}
