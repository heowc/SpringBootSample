package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Message implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String content;

    public Message() {}

    public Message(String content) {
        this.content = content;
    }
}
