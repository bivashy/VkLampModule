package com.bivashy.lamp.commands.vk.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bivashy.lamp.commands.vk.exceptions.InvalidConversationType;
import com.vk.api.sdk.objects.messages.ConversationPeerType;

import revxrsal.commands.annotation.DistributeOnMethods;

@DistributeOnMethods
@Retention(RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface ConversationType {
	/**
	 * Conversation types that allowed for the command
	 *
	 * For example:
	 *
	 * @ConversationType(conversationTypes = {ConversationPeerType.CHAT}) public
	 *                                     void command(){
	 *
	 *                                     }
	 *
	 *                                     will throw
	 *                                     {@link InvalidConversationType} exception
	 *                                     if player sended from the wrong
	 *                                     conversation type
	 *
	 * @return
	 */
	ConversationPeerType[] conversationTypes();
}
