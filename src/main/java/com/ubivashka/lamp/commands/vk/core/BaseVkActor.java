package com.ubivashka.lamp.commands.vk.core;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import com.ubivashka.lamp.commands.vk.VkActor;
import com.ubivashka.lamp.commands.vk.VkCommandHandler;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Conversation;
import com.vk.api.sdk.objects.messages.ConversationPeerType;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserFull;

public class BaseVkActor implements VkActor {
	private final Supplier<UUID> uuid = MemoizingSupplier.memoize(() -> new UUID(0, getAuthorId()));
	private final VkCommandHandler commandHandler;
	private final Message message;

	private final Supplier<UserFull> user;

	private final Supplier<Conversation> conversation;

	public BaseVkActor(Message message, VkCommandHandler commandHandler) {
		this.message = message;
		this.commandHandler = commandHandler;

		user = MemoizingSupplier.memoize(() -> {
			try {
				return commandHandler.getClient().users().get(commandHandler.getActor())
						.userIds(String.valueOf(getAuthorId())).execute().get(0);
			} catch (ApiException | ClientException e) {
				e.printStackTrace();
				return null;
			}

		});

		conversation = MemoizingSupplier.memoize(() -> {
			try {
				return commandHandler.getClient().messages()
						.getConversationsById(commandHandler.getActor(), message.getPeerId()).execute().getItems()
						.get(0);
			} catch (ApiException | ClientException e) {
				e.printStackTrace();
				return null;
			}

		});
	}

	@Override
	public String getName() {
		return getUser().getFirstName();
	}

	@Override
	public UUID getUniqueId() {
		return uuid.get();
	}

	@Override
	public void reply(String message) {
		try {
			commandHandler.getClient().messages().send(commandHandler.getActor())
					.randomId(ThreadLocalRandom.current().nextInt()).peerId(getPeerId()).message(message).execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void error(String message) {
		try {
			commandHandler.getClient().messages().send(commandHandler.getActor())
					.randomId(ThreadLocalRandom.current().nextInt()).peerId(getPeerId()).message(message).execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message getMessage() {
		return message;
	}

	@Override
	public UserFull getUser() {
		return user.get();
	}

	@Override
	public Conversation getConversation() {
		return conversation.get();
	}

	@Override
	public ConversationPeerType getConversationType() {
		return getConversation().getPeer().getType();
	}

	@Override
	public String getMessageText() {
		return message.getText();
	}

	@Override
	public String getMessagePayload() {
		return message.getPayload();
	}

	@Override
	public Integer getConversationMessageId() {
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

//

}
