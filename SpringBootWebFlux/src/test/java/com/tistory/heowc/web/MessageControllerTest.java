package com.tistory.heowc.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MessageControllerTest {

    private WebTestClient webClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080/").build();

    private Logger logger = Logger.getLogger(MessageControllerTest.class.getName());

    @Test
    public void test_findByOne() {
        webClient.get()
                .uri("message/{idx}", 1L)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    public void test_insert() {
        webClient.post()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"content\": \"h2\"}")
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    public void test_update() {
        webClient.put()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"idx\": 1, \"content\": \"h2\"}")
                .exchange().expectBody(String.class)
                .consumeWith(result -> logger.info(result.getResponseBody()));
    }

    @Test
    public void test_deleteByIdx() {
        webClient.delete()
                .uri("message/{idx}", 1L)
                .exchange().expectStatus().isOk();
    }

}