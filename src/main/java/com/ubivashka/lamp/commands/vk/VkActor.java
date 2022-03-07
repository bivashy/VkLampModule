package com.ubivashka.lamp.commands.vk;

import com.ubivashka.lamp.commands.vk.core.BaseVkActor;
import com.vk.api.sdk.objects.messages.Conversation;
import com.vk.api.sdk.objects.messages.ConversationPeerType;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserFull;

import revxrsal.commands.command.CommandActor;

/**
 * Represents a VK {@link CommandActor} that executes a command, whether in a
 * user message or a conversation chat message.
 */
public interface VkActor extends CommandActor {

	/**
	 * Returns the message of the actor
	 *
	 * @return The actor`s sent message
	 */
	Message getMessage();

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
	 * Returns text of message
	 *
	 * @return Message text
	 */
	String getMessageText();

	/**
	 * Returns payload of message
	 *
	 * @return Message payload
	 */
	String getMessagePayload();

	/**
	 * Returns unique auto-incremented number for all messages with this peer
	 *
	 * @return Message conversation id
	 */
	Integer getConversationMessageId();

	/**
	 * Returns message author id that can be passed to the method
	 * {@link com.vk.api.sdk.client.VkApiClient#users()}
	 *
	 * @return Message author id
	 */
	Integer getAuthorId();

	/**
	 * Returns message peed id that represents conversation id
	 *
	 * @return Message peer id
	 */
	Integer getPeerId();

	/**
	 * Wraps message, and command handler to the {@link VkActor}
	 *
	 * @param message
	 * @param vkCommandHandler
	 * @return {@link VkActor} that wrapped parameters
	 */
	static VkActor wrap(Message message, VkCommandHandler vkCommandHandler) {
		return new BaseVkActor(message, vkCommandHandler);
	}
}
