package com.ubivashka.lamp.commands.vk.core;

import com.ubivashka.lamp.commands.vk.VkActor;
import com.ubivashka.lamp.commands.vk.message.DispatchSource;
import com.vk.api.sdk.objects.messages.Conversation;
import com.vk.api.sdk.objects.users.UserFull;

import revxrsal.commands.command.CommandActor;
import revxrsal.commands.command.ExecutableCommand;
import revxrsal.commands.process.SenderResolver;

public class VkSenderResolver implements SenderResolver {
	public static final VkSenderResolver INSTANCE = new VkSenderResolver();

	private VkSenderResolver() {
	}

	@Override
	public boolean isCustomType(Class<?> type) {
		return UserFull.class.isAssignableFrom(type) || Conversation.class.isAssignableFrom(type)
				|| DispatchSource.class.isAssignableFrom(type);
	}

	@Override
	public Object getSender(Class<?> customSenderType, CommandActor actor, ExecutableCommand command) {
		VkActor vkActor = (VkActor) actor;
		if (UserFull.class.isAssignableFrom(customSenderType))
			return vkActor.getUser();
		if (Conversation.class.isAssignableFrom(customSenderType))
			return vkActor.getConversation();
		if (DispatchSource.class.isAssignableFrom(customSenderType))
			return vkActor.getDispatchSource();
		return vkActor;
	}

}
