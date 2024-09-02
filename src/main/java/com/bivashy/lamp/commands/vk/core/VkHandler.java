package com.bivashy.lamp.commands.vk.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import api.longpoll.bots.VkBot;
import api.longpoll.bots.methods.VkBotsMethods;
import com.bivashy.lamp.commands.vk.VkActor;
import com.bivashy.lamp.commands.vk.VkCommandHandler;
import com.bivashy.lamp.commands.vk.annotations.ConversationType;
import com.bivashy.lamp.commands.vk.exceptions.InvalidConversationType;
import com.bivashy.lamp.commands.vk.exceptions.VkExceptionAdapter;
import revxrsal.commands.core.BaseCommandHandler;

public class VkHandler extends BaseCommandHandler implements VkCommandHandler {

    // TODO: Remove that redundant list
    private static final List<VkHandler> INSTANCES = Collections.synchronizedList(new ArrayList<>());
    private final VkBot bot;
    private final VkBotsMethods methods;

    public VkHandler(VkBot bot) {
        super();
        this.bot = bot;
        this.methods = new VkBotsMethods(this.bot::getAccessToken);

        registerCondition((actor, command, arguments) -> {
            if (!command.hasAnnotation(ConversationType.class))
                return;

            String[] allowedConversationTypes = command.getAnnotation(ConversationType.class)
                    .conversationTypes();

            String conversationType = actor.as(VkActor.class).getConversationType().getType();
            if (Arrays.stream(allowedConversationTypes)
                    .noneMatch(peerType -> peerType.equals(conversationType)))
                throw new InvalidConversationType(command);
        });

        registerDependency(VkBot.class, this.bot);
        registerDependency(VkBotsMethods.class, this.methods);

        setExceptionHandler(VkExceptionAdapter.INSTANCE);

        INSTANCES.add(this);
    }

    @Override
    public VkBot getClient() {
        return bot;
    }

    @Override
    public VkBotsMethods vk() {
        return methods;
    }

    public static List<VkHandler> getInstances() {
        return Collections.unmodifiableList(INSTANCES);
    }

}
