package com.ubivashka.lamp.commands.vk;

import com.ubivashka.lamp.commands.vk.api.actor.VkApiActor;
import com.vk.api.sdk.client.VkApiClient;

import revxrsal.commands.CommandHandler;

public interface VkCommandHandler extends CommandHandler {
    /**
     * Returns {@link VkApiClient} that handles message events
     *
     * @return vk client
     */
    VkApiClient getClient();

    /**
     * Returns vk api actor that implements methods for query creating by actor
     *
     * @return vk api actor
     */
    VkApiActor getActor();
}
