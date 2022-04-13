package com.ubivashka.lamp.commands.vk.message;

/**
 * Represents dispatch source that can be as message or callback button with
 * custom id (payload)
 *
 */
public interface DispatchSource {
	String getText();

	String getPayload();

	Integer getConversationId();

	Integer getAuthorId();

	Integer getPeerId();
	
	default <T extends DispatchSource> T as(Class<T> clazz) {
		return clazz.cast(this);
	}
}
