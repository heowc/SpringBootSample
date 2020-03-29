package com.tistory.heowc.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(MessageControllerTest.class);

    @LocalServerPort
    private int port;

    private WebTestClient webClient;

    @BeforeEach
    void init() {
        webClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
    }
    @Test
    void test_findByOne() {
        webClient.get()
                .uri("message/{idx}", 1L)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    void test_insert() {
        webClient.post()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"content\": \"h2\"}")
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    void test_update() {
        webClient.put()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"idx\": 1, \"content\": \"h2\"}")
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    void test_deleteByIdx() {
        webClient.delete()
                .uri("message/{idx}", 1L)
                .exchange().expectStatus().isOk();
    }
}