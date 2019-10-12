package com.example;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class MessageResourceAssembler extends RepresentationModelAssemblerSupport<Message, MessageModel> {

    public MessageResourceAssembler() {
        super(MessageController.class, MessageModel.class);
    }

    @Override
    public MessageModel toModel(Message entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected MessageModel instantiateModel(Message entity) {
        return new MessageModel(entity);
    }
}
