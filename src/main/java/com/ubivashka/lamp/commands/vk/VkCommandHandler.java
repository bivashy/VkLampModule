package com.ubivashka.lamp.commands.vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

import revxrsal.commands.CommandHandler;

public interface VkCommandHandler extends CommandHandler {
	/**
	 * Returns {@link VkApiClient} that handles message events
	 *
	 * @return vk client
	 */
	VkApiClient getClient();

	/**
	 * Returns group that received message event
	 *
	 * @return
	 */
	GroupActor getActor();
}
