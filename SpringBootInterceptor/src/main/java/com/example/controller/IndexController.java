package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @GetMapping(value = "/")
    public ResponseEntity<?> index() {
        String msg = "Hello, Spring Boot Interceptor";
        logger.info(msg);
        return ResponseEntity.ok()
                .header("controller", "controller")
                .body(msg);
    }
}