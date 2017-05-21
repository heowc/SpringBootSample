package com.tistory.heowc.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    private WebTestClient webClient;

    @Before
    public void before_init() throws Exception {
        webClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080/").build();
    }

    @Test
    public void test_findByOne() throws Exception {
        webClient.get()
                .uri("message/{idx}", 1L)
                .exchange().expectBody()
                .consumeAsStringWith(System.out::println);
    }

    @Test
    public void test_insert() throws Exception {
        webClient.post()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"content\": \"h2\"}")
                .exchange().expectBody()
                .consumeAsStringWith(System.out::println);
    }

    @Test
    public void test_update() throws Exception {
        webClient.put()
                .uri("message")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"idx\": 1, \"content\": \"h2\"}")
                .exchange().expectBody()
                .consumeAsStringWith(System.out::println);
    }

    @Test
    public void test_deleteByIdx() throws Exception {
        webClient.delete()
                .uri("message/{idx}", 1L)
                .exchange().expectStatus().isOk();
    }

}