package com.heowc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootSseTests {

    @LocalServerPort
    private long port;

    @Test
    void test() {
        final ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<>() {};
        final WebClient client = WebClient.create(String.format("http://localhost:%d", port));

        final Flux<ServerSentEvent<String>> exchanged = client.get()
                                                              .uri("/members/stream-all")
                                                              .exchangeToFlux(res -> res.bodyToFlux(type))
                                                              .log();

        StepVerifier.create(exchanged)
                    .expectNextCount(5)
                    .expectComplete()
                    .verify();
    }
}
