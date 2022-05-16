package com.ubivashka.lamp.commands.vk.message;

import revxrsal.commands.CommandHandler;
import revxrsal.commands.command.ArgumentStack;

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
	
	Integer getSourceId();
	
	default ArgumentStack getArgumentStack(CommandHandler commandHandler) {
		return commandHandler.parseArguments(getText());
	}

	default <T extends DispatchSource> T as(Class<T> clazz) {
		return clazz.cast(this);
	}
}
