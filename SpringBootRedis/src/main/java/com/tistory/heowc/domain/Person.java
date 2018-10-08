package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;
    private String firstname;
    private String lastname;
}