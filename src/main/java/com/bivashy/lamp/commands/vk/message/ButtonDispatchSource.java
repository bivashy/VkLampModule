package com.bivashy.lamp.commands.vk.message;

import api.longpoll.bots.model.events.messages.MessageEvent;

public class ButtonDispatchSource implements DispatchSource {

    private final MessageEvent messageEvent;

    public ButtonDispatchSource(MessageEvent messageEvent) {
        this.messageEvent = messageEvent;
    }

    @Override
    public String getText() {
        return messageEvent.getPayload().toString();
    }

    @Override
    public String getPayload() {
        return messageEvent.toString();
    }

    @Override
    public Integer getConversationId() {
        return Integer.parseInt(messageEvent.getConversationMessageId());
    }

    @Override
    public Integer getAuthorId() {
        return messageEvent.getUserId();
    }

    @Override
    public Integer getPeerId() {
        return messageEvent.getPeerId();
    }

}
