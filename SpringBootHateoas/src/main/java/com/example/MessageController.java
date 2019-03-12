package com.example;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Clock;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/{id}")
    public ResponseEntity<MessageResource> get(@PathVariable Long id) throws URISyntaxException {
        Message message = new Message(id, "hello", LocalDateTime.now(Clock.systemUTC()));

        MessageResourceAssembler assembler = new MessageResourceAssembler();
        MessageResource resource = assembler.toResource(message);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(resource.getLink(Link.REL_SELF).getHref()));
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}

