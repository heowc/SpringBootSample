package com.example;

import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

public class MessageResource extends ResourceSupport {

    private String content;
    private LocalDateTime createdAt;

    protected MessageResource() { }

    public MessageResource(Message message) {
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}