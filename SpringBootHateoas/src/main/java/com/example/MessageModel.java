package com.example;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class MessageModel extends RepresentationModel<MessageModel> {

    private String content;
    private LocalDateTime createdAt;

    protected MessageModel() { }

    public MessageModel(Message message) {
        content = message.getContent();
        createdAt = message.getCreatedAt();
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