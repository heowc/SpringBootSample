package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootGracefulShutdownApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder()
                .sources(SpringBootGracefulShutdownApplication.class)
                .listeners(new ApplicationPidFileWriter("./application.pid"))
                .build();

        application.run(args);
    }

    @RestController
    public static class LongProcessController {

        @GetMapping
        public String pause() throws InterruptedException {
            Thread.sleep(5_000L);
            return "Process finished";
        }

    }
}
