package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BasicService {

    private static final Logger logger = Logger.getLogger(BasicService.class.getName());

    @Async
    public void onAsync() {
        try {
            Thread.sleep(1000);
            logger.info("onAsync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onSync() {
        try {
            Thread.sleep(1000);
            logger.info("onSync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
