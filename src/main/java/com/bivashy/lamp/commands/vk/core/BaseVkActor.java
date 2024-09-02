package com.bivashy.lamp.commands.vk.core;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Conversation;
import api.longpoll.bots.model.objects.basic.User;
import com.bivashy.lamp.commands.vk.VkActor;
import com.bivashy.lamp.commands.vk.VkCommandHandler;
import com.bivashy.lamp.commands.vk.message.DispatchSource;
import revxrsal.commands.CommandHandler;

public class BaseVkActor implements VkActor {

    private final Supplier<UUID> uuid = MemoizingSupplier.memoize(() -> new UUID(0, getAuthorId()));
    private final VkCommandHandler commandHandler;
    private final DispatchSource dispatchSource;
    private final Supplier<User> user;
    private final Supplier<Conversation> conversation;

    public BaseVkActor(DispatchSource dispatchSource, VkCommandHandler commandHandler) {
        this.dispatchSource = dispatchSource;
        this.commandHandler = commandHandler;

        user = MemoizingSupplier.memoize(() -> {
            try {
                return commandHandler.vk().users.get().setUserIds(Integer.toString(getAuthorId())).execute().getResponse().get(0);
            } catch (VkApiException e) {
                // TODO: Actual exception handling
                e.printStackTrace();
                return null;
            }
        });

        conversation = MemoizingSupplier.memoize(() -> {
            try {
                return commandHandler.vk().messages.getConversationsById().setPeerIds(dispatchSource.getPeerId()).execute().getResponse().getItems().get(0);
            } catch (VkApiException e) {
                // TODO: Actual exception handling
                e.printStackTrace();
                return null;
            }
        });
        commandHandler.registerSenderResolver(VkSenderResolver.INSTANCE);
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
        commandHandler.vk().messages.send().setRandomId(ThreadLocalRandom.current().nextInt()).setPeerId(getPeerId()).setMessage(message).executeAsync();
    }

    @Override
    public void error(String message) {
        reply(message);
    }

    @Override
    public Conversation.Peer getConversationType() {
        return getConversation().getPeer();
    }

    @Override
    public User getUser() {
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
