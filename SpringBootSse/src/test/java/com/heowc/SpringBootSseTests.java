package com.heowc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootSseTests {

    @LocalServerPort
    private long port;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void test() {
        restTemplate.execute(String.format("http://localhost:%d/members/stream-all", port), HttpMethod.GET, request -> {
        }, response -> {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody()));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                //Something clever
            }
            return response;
        });
    }
}
