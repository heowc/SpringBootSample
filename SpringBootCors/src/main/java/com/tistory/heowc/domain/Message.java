package com.tistory.heowc.domain;

import java.io.Serializable;

public class Message implements Serializable {

    private String sender;
    private String content;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }
}
