package com.bivashy.lamp.commands.vk.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bivashy.lamp.commands.vk.VkActor;
import com.bivashy.lamp.commands.vk.annotations.ConversationType;
import com.bivashy.lamp.commands.vk.exceptions.InvalidConversationType;
import com.bivashy.lamp.commands.vk.exceptions.VkExceptionAdapter;
import com.bivashy.lamp.commands.vk.VkCommandHandler;
import com.bivashy.lamp.commands.vk.api.actor.VkApiActor;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.ConversationPeerType;

import revxrsal.commands.core.BaseCommandHandler;

public class VkHandler extends BaseCommandHandler implements VkCommandHandler {
    private static final List<VkHandler> INSTANCES = Collections.synchronizedList(new ArrayList<>());
    private final VkApiClient vkApiClient;
    private final VkApiActor apiActor;

    @Deprecated
    public VkHandler(VkApiClient vkApiClient, GroupActor groupActor) {
        this(vkApiClient, VkApiActor.of(groupActor));
    }

    public VkHandler(VkApiClient vkApiClient, VkApiActor apiActor) {
        super();
        this.vkApiClient = vkApiClient;
        this.apiActor = apiActor;

        registerCondition((actor, command, arguments) -> {
            if (!command.hasAnnotation(ConversationType.class))
                return;

            ConversationPeerType[] allowedConversationTypes = command.getAnnotation(ConversationType.class)
                    .conversationTypes();

            if (Arrays.stream(allowedConversationTypes)
                    .noneMatch(peerType -> peerType == actor.as(VkActor.class).getConversationType()))
                throw new InvalidConversationType(command);
        });

        registerDependency(VkApiClient.class, vkApiClient);
        registerDependency(VkApiActor.class, apiActor);
        registerDependency(Actor.class, apiActor.getActor());

        setExceptionHandler(VkExceptionAdapter.INSTANCE);

        INSTANCES.add(this);
    }

    @Override
    public VkApiClient getClient() {
        return vkApiClient;
    }

    @Override
    public VkApiActor getActor() {
        return apiActor;
    }

    public static List<VkHandler> getInstances() {
        return Collections.unmodifiableList(INSTANCES);
    }

}
