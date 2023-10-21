package com.heowc;

import com.heowc.domain.Member;
import com.heowc.domain.MemberRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSseApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(MemberRepository repository) {
        return args -> {
            final LocalDateTime now = LocalDateTime.now();
            repository.saveAll(List.of(
               new Member(now),
               new Member(now),
               new Member(now),
               new Member(now),
               new Member(now),
               new Member(now.minusDays(1)),
               new Member(now.minusDays(1)),
               new Member(now.minusDays(1))
            ));
        };
    }
}
