package com.bivashy.lamp.commands.vk.api.actor;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsByIdQuery;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import com.vk.api.sdk.queries.users.UsersGetQuery;

/**
 * Represents an {@link com.vk.api.sdk.client.actors.GroupActor} or {@link com.vk.api.sdk.client.actors.UserActor}
 */
public interface VkApiActor {
    static VkApiActor of(UserActor userActor) {
        return new UserVkApiActor(userActor);
    }

    static VkApiActor of(GroupActor groupActor) {
        return new GroupVkApiActor(groupActor);
    }

    Actor getActor();

    MessagesSendQuery sendMessage(VkApiClient client);

    UsersGetQuery usersGet(VkApiClient client);

    MessagesGetConversationsByIdQuery conversationById(VkApiClient client);

    MessagesGetConversationsByIdQuery conversationById(VkApiClient client, int peerId);
}
