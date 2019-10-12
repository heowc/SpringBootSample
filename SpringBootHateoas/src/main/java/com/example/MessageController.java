package com.example;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/{id}")
    public ResponseEntity<MessageModel> get(@PathVariable Long id) {
        Message message = new Message(id, "hello", LocalDateTime.now(Clock.systemUTC()));

        MessageResourceAssembler assembler = new MessageResourceAssembler();
        MessageModel model = assembler.toModel(message);

        HttpHeaders headers = new HttpHeaders();
        model.getLink(IanaLinkRelations.SELF).ifPresent(link -> headers.setLocation(link.toUri()));
        return ResponseEntity.ok().headers(headers).body(model);
    }
}

