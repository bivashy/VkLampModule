package com.ubivashka.lamp.commands.vk.core;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import com.ubivashka.lamp.commands.vk.VkActor;
import com.ubivashka.lamp.commands.vk.VkCommandHandler;
import com.ubivashka.lamp.commands.vk.message.DispatchSource;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Conversation;
import com.vk.api.sdk.objects.messages.ConversationPeerType;
import com.vk.api.sdk.objects.users.UserFull;

import revxrsal.commands.CommandHandler;

public class BaseVkActor implements VkActor {
    private final Supplier<UUID> uuid = MemoizingSupplier.memoize(() -> new UUID(0, getAuthorId()));
    private final VkCommandHandler commandHandler;
    private final DispatchSource dispatchSource;

    private final Supplier<UserFull> user;

    private final Supplier<Conversation> conversation;

    public BaseVkActor(DispatchSource dispatchSource, VkCommandHandler commandHandler) {
        this.dispatchSource = dispatchSource;
        this.commandHandler = commandHandler;

        user = MemoizingSupplier.memoize(() -> {
            try {
                return commandHandler.getActor().usersGet(commandHandler.getClient())
                        .userIds(String.valueOf(getAuthorId())).execute().get(0);
            } catch(ApiException | ClientException e) {
                commandHandler.getExceptionHandler().handleException(e, this);
                return null;
            }

        });

        conversation = MemoizingSupplier.memoize(() -> {
            try {
                return commandHandler.getActor().conversationById(commandHandler.getClient(), dispatchSource.getPeerId()).execute()
                        .getItems().get(0);
            } catch(ApiException | ClientException e) {
                commandHandler.getExceptionHandler().handleException(e, this);
                return null;
            }

        });
    }

    @Override
    public String getName() {
        return String.valueOf(dispatchSource.getAuthorId());
    }

    @Override
    public UUID getUniqueId() {
        return uuid.get();
    }

    @Override
    public DispatchSource getDispatchSource() {
        return dispatchSource;
    }

    @Override
    public void reply(String message) {
        try {
            commandHandler.getActor().sendMessage(commandHandler.getClient())
                    .randomId(ThreadLocalRandom.current().nextInt()).peerId(getPeerId()).message(message).execute();
        } catch(ApiException | ClientException e) {
            commandHandler.getExceptionHandler().handleException(e, this);
        }
    }

    @Override
    public void error(String message) {
        try {
            commandHandler.getActor().sendMessage(commandHandler.getClient())
                    .randomId(ThreadLocalRandom.current().nextInt()).peerId(getPeerId()).message(message).execute();
        } catch(ApiException | ClientException e) {
            commandHandler.getExceptionHandler().handleException(e, this);
        }
    }

    @Override
    public ConversationPeerType getConversationType() {
        return getConversation().getPeer().getType();
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
    public String getText() {
        return dispatchSource.getText();
    }

    @Override
    public String getMessagePayload() {
        return dispatchSource.getPayload();
    }

    @Override
    public Integer getConversationId() {
        return dispatchSource.getConversationId();
    }

    @Override
    public Integer getAuthorId() {
        return dispatchSource.getAuthorId();
    }

    @Override
    public Integer getPeerId() {
        return dispatchSource.getPeerId();
    }

    @Override
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
