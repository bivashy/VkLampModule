package com.ubivashka.lamp.commands.vk.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ubivashka.lamp.commands.vk.VkActor;
import com.ubivashka.lamp.commands.vk.VkCommandHandler;
import com.ubivashka.lamp.commands.vk.annotations.ConversationType;
import com.ubivashka.lamp.commands.vk.exceptions.InvalidConversationType;
import com.ubivashka.lamp.commands.vk.exceptions.VkExceptionAdapter;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.ConversationPeerType;

import revxrsal.commands.core.BaseCommandHandler;

public class VkHandler extends BaseCommandHandler implements VkCommandHandler {
	private static final List<VkHandler> INSTANCES = Collections.synchronizedList(new ArrayList<>());
	private final VkApiClient vkApiClient;
	private final GroupActor groupActor;

	public VkHandler(VkApiClient vkApiClient, GroupActor groupActor) {
		super();
		this.vkApiClient = vkApiClient;
		this.groupActor = groupActor;

		registerCondition((actor, command, arguments) -> {
			if (!command.hasAnnotation(ConversationType.class))
				return;

			ConversationPeerType[] allowedConversationTypes = command.getAnnotation(ConversationType.class)
					.conversationTypes();

			if (!Arrays.stream(allowedConversationTypes)
					.anyMatch(peerType -> peerType == actor.as(VkActor.class).getConversationType()))
				throw new InvalidConversationType(command);
		});

		registerDependency(VkApiClient.class, vkApiClient);
		registerDependency(GroupActor.class, groupActor);

		setExceptionHandler(VkExceptionAdapter.INSTANCE);

		INSTANCES.add(this);
	}

	@Override
	public VkApiClient getClient() {
		return vkApiClient;
	}

	@Override
	public GroupActor getActor() {
		return groupActor;
	}

	public static List<VkHandler> getInstances() {
		return Collections.unmodifiableList(INSTANCES);
	}

}
