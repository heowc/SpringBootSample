package com.example;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/{id}")
    public ResponseEntity<MessageResource> get(@PathVariable Long id) {
        Message message = new Message(id, "hello", LocalDateTime.now(Clock.systemUTC()));
        MessageResource messageResource = message.toResource();

//        1)
//        Link link = new Link("http://localhost:8080/message/" + id);
//        2)
        ControllerLinkBuilder linkBuilder = linkTo(MessageController.class).slash(message);
        Link link = linkBuilder.withSelfRel();

        messageResource.add(link);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}

