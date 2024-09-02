package com.bivashy.lamp.commands.vk.message;

import api.longpoll.bots.model.events.messages.MessageNew;

public class MessageDispatchSource implements DispatchSource {

    private final MessageNew messageNewEvent;

    public MessageDispatchSource(MessageNew messageNewEvent) {
        this.messageNewEvent = messageNewEvent;
    }

    @Override
    public String getText() {
        return messageNewEvent.getMessage().getText();
    }

    @Override
    public String getPayload() {
        return messageNewEvent.getMessage().getPayload().toString();
    }

    @Override
    public Integer getConversationId() {
        return messageNewEvent.getMessage().getConversationMessageId();
    }

    @Override
    public Integer getAuthorId() {
        return messageNewEvent.getMessage().getFromId();
    }

    @Override
    public Integer getPeerId() {
        return messageNewEvent.getMessage().getPeerId();
    }

}
