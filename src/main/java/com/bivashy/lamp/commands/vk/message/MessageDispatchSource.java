package com.bivashy.lamp.commands.vk.message;

import api.longpoll.bots.model.events.messages.MessageNew;
import com.google.gson.JsonElement;

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
        return getRawPayload();
    }

    private String getRawPayload() {
        JsonElement element = messageNewEvent.getMessage().getPayload();
        return element.isJsonPrimitive() ? element.getAsString() : element.toString();
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
