package com.example;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class MessageResourceAssembler extends ResourceAssemblerSupport<Message, MessageResource> {

    public MessageResourceAssembler() {
        super(MessageController.class, MessageResource.class);
    }

    @Override
    public MessageResource toResource(Message entity) {
        return createResourceWithId(entity.getId(), entity);
    }

    @Override
    protected MessageResource instantiateResource(Message entity) {
        return new MessageResource(entity);
    }
}
