package com.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.stream.IntStream;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired AsyncRestTemplate asyncRestTemplate;

    @Override
    public void run(String... args) throws Exception {

        IntStream.range(0, 10)
                .mapToObj(idx -> getRepositoryInfo("Heo-Won-Chul"))
                .forEach(action -> action.addCallback(
                        System.out::println,
                        System.out::println
                ));
    }

    private ListenableFuture<ResponseEntity<String>> getRepositoryInfo(String userName) {
        return asyncRestTemplate
                .getForEntity("https://api.github.com/users/" + userName + "/repos", String.class);
    }
}
