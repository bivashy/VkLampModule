package com.ubivashka.lamp.commands.vk.message;

import com.vk.api.sdk.objects.messages.Message;

public class MessageDispatchSource implements DispatchSource{
	private final Message message;

	public MessageDispatchSource(Message message) {
		this.message = message;
	}

	@Override
	public String getText() {
		return message.getText();
	}

	@Override
	public String getPayload() {
		return message.getPayload();
	}

	@Override
	public Integer getConversationId() {
		return message.getConversationMessageId();
	}

	@Override
	public Integer getAuthorId() {
		return message.getFromId();
	}

	@Override
	public Integer getPeerId() {
		return message.getPeerId();
	}
}
