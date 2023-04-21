package com.bivashy.lamp.commands.vk;

import com.bivashy.lamp.commands.vk.core.BaseVkActor;
import com.bivashy.lamp.commands.vk.message.DispatchSource;
import com.vk.api.sdk.objects.messages.Conversation;
import com.vk.api.sdk.objects.messages.ConversationPeerType;
import com.vk.api.sdk.objects.users.UserFull;

import revxrsal.commands.command.CommandActor;

/**
 * Represents a VK {@link CommandActor} that executes a command, whether in a
 * user message or a conversation chat message.
 */
public interface VkActor extends CommandActor {

	/**
	 * Returns the source of the dispatch
	 *
	 * @return The actor`s sent dispatch object
	 */
	DispatchSource getDispatchSource();

	/**
	 * Returns the user from the message <br>
	 * <strong>This method sends request to the vk api in first execution</strong>
	 *
	 * @return Actor as Vk user
	 */
	UserFull getUser();

	/**
	 * Returns conversation of the message <br>
	 * <strong>This method sends request to the vk api in first execution</strong>
	 *
	 * @return Message conversation
	 */
	Conversation getConversation();
	
	/**
	 * Returns conversation type of message {@link ConversationPeerType}
	 *
	 * @return Message conversation type
	 */
	ConversationPeerType getConversationType();

	/**
	 * Returns text of dispatch source
	 *
	 * @return Dispatch source text
	 */
	String getText();

	/**
	 * Returns payload of dispatch source
	 *
	 * @return Dispatch source payload
	 */
	String getMessagePayload();

	/**
	 * Returns unique auto-incremented number for all messages with this peer
	 *
	 * @return Dispatch source conversation id
	 */
	Integer getConversationId();

	/**
	 * Returns message author id that can be passed to the method
	 * {@link com.vk.api.sdk.client.VkApiClient#users()}
	 *
	 * @return Author that dispatched
	 */
	Integer getAuthorId();

	/**
	 * Returns source peer id that represents conversation id
	 *
	 * @return Source peer id
	 */
	Integer getPeerId();

	/**
	 * Wraps dispatch source, and command handler to the {@link VkActor}
	 *
	 * @param message
	 * @param vkCommandHandler
	 * @return {@link VkActor} that wrapped parameters
	 */
	static VkActor wrap(DispatchSource dispatchSource, VkCommandHandler vkCommandHandler) {
		return new BaseVkActor(dispatchSource, vkCommandHandler);
	}
}
