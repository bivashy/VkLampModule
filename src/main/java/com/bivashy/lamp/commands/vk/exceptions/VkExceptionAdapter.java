package com.bivashy.lamp.commands.vk.exceptions;

import revxrsal.commands.command.CommandActor;
import revxrsal.commands.exception.DefaultExceptionHandler;
import revxrsal.commands.exception.InvalidCommandException;

public class VkExceptionAdapter extends DefaultExceptionHandler {
	public static final VkExceptionAdapter INSTANCE = new VkExceptionAdapter();

	private VkExceptionAdapter() {
	}

	@Override
	public void invalidCommand(CommandActor actor, InvalidCommandException ignored) {
	}

	public void invalidConversationType(CommandActor actor, InvalidConversationType exception) {
		actor.reply("This command cannot be executed here");
	}
}
