package com.example;

import org.springframework.hateoas.Identifiable;

import java.time.LocalDateTime;

public class Message implements Identifiable<Long> {

    private Long id;
    private String content;
    private LocalDateTime createdAt;

    protected Message() { }

    public Message(Long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
