package com.ubivashka.lamp.commands.vk.message;

import com.ubivashka.lamp.commands.vk.objects.CallbackButton;

public class ButtonDispatchSource implements DispatchSource {
	private final CallbackButton callbackButton;

	public ButtonDispatchSource(CallbackButton callbackButton) {
		this.callbackButton = callbackButton;
	}

	@Override
	public String getText() {
		return callbackButton.getPayload();
	}

	@Override
	public String getPayload() {
		return callbackButton.getPayload();
	}

	@Override
	public Integer getConversationId() {
		return callbackButton.getConversationMessageID();
	}

	@Override
	public Integer getAuthorId() {
		return callbackButton.getUserID();
	}

	@Override
	public Integer getPeerId() {
		return callbackButton.getPeerID();
	}
	
	@Override
	public Integer getSourceId() {
		return 0;
	}
	
	public CallbackButton getCallbackButton() {
		return callbackButton;
	}
}
