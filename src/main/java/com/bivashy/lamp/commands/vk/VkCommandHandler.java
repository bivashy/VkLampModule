package com.bivashy.lamp.commands.vk;

import api.longpoll.bots.VkBot;
import api.longpoll.bots.methods.VkBotsMethods;
import revxrsal.commands.CommandHandler;

public interface VkCommandHandler extends CommandHandler {

    /**
     * Returns {@link VkBot} that handles message events
     *
     * @return vk client
     */
    VkBot getClient();

    /**
     * Returns {@link VkBotsMethods} which allows to send API requests
     */
    VkBotsMethods vk();

}
