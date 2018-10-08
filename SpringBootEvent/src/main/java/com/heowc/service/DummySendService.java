package com.heowc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummySendService implements SendService {

    private static final Logger logger = LoggerFactory.getLogger(DummySendService.class);

    @Override
    public void send(String message) {
        logger.info(message);
    }
}
