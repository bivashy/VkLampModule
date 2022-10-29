package com.ubivashka.lamp.commands.vk.api.actor;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsByIdQuery;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import com.vk.api.sdk.queries.users.UsersGetQuery;

public class GroupVkApiActor implements VkApiActor {
    private final GroupActor actor;

    public GroupVkApiActor(GroupActor actor) {
        this.actor = actor;
    }

    @Override
    public Actor getActor() {
        return actor;
    }

    @Override
    public MessagesSendQuery sendMessage(VkApiClient client) {
        return client.messages().send(actor);
    }

    @Override
    public UsersGetQuery usersGet(VkApiClient client) {
        return client.users().get(actor);
    }

    @Override
    public MessagesGetConversationsByIdQuery conversationById(VkApiClient client, int peerId) {
        return client.messages().getConversationsById(actor, peerId);
    }

    @Override
    public MessagesGetConversationsByIdQuery conversationById(VkApiClient client) {
        return client.messages().getConversationsById(actor);
    }
}
