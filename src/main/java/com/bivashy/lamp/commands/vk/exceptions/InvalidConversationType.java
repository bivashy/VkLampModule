package com.bivashy.lamp.commands.vk.exceptions;

import revxrsal.commands.command.ExecutableCommand;
import revxrsal.commands.exception.ThrowableFromCommand;

@ThrowableFromCommand
public class InvalidConversationType extends RuntimeException {
	private final ExecutableCommand command;

	public InvalidConversationType(ExecutableCommand command) {
		this.command = command;
	}

	public ExecutableCommand getCommand() {
		return command;
	}
}
