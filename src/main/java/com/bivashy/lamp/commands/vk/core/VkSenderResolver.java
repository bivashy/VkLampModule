package com.bivashy.lamp.commands.vk.core;

import api.longpoll.bots.model.objects.basic.Conversation;
import api.longpoll.bots.model.objects.basic.User;
import com.bivashy.lamp.commands.vk.VkActor;
import com.bivashy.lamp.commands.vk.message.DispatchSource;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.command.ExecutableCommand;
import revxrsal.commands.process.SenderResolver;

public class VkSenderResolver implements SenderResolver {

    public static final VkSenderResolver INSTANCE = new VkSenderResolver();

    private VkSenderResolver() {
    }

    @Override
    public boolean isCustomType(Class<?> type) {
        return User.class.isAssignableFrom(type) || Conversation.class.isAssignableFrom(type) || DispatchSource.class.isAssignableFrom(type);
    }

    @Override
    public Object getSender(Class<?> customSenderType, CommandActor actor, ExecutableCommand command) {
        VkActor vkActor = (VkActor) actor;
        if (User.class.isAssignableFrom(customSenderType))
            return vkActor.getUser();
        if (Conversation.class.isAssignableFrom(customSenderType))
            return vkActor.getConversation();
        if (DispatchSource.class.isAssignableFrom(customSenderType))
            return vkActor.getDispatchSource();
        return vkActor;
    }

}
