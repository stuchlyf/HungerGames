package de.stuchlyf.hungergames.discordgw.business;


import de.stuchlyf.hungergames.discordgw.business.command.Command;
import de.stuchlyf.hungergames.discordgw.business.command.handler.CommandHandler;
import discord4j.core.object.entity.Message;
import lombok.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Consumer;

public abstract class BaseMessageListener {

	@Getter
	private final List<Consumer<Message>> middlewares;
	@Getter
	private final Map<Command, CommandHandler> handlers;

	@Getter
	private final Character commandPrefix;

	protected BaseMessageListener(Character commandPrefix, List<CommandHandler> commandHandlers) {
		this.commandPrefix = commandPrefix;
		this.middlewares = new ArrayList<>();
		this.handlers = new EnumMap<>(Command.class);

		commandHandlers.forEach(commandHandler ->  this.registerHandler(commandHandler.getHandlerFor(), commandHandler));
	}

	public Mono<Void> processCommand(Message eventMessage) {
		return Mono.just(eventMessage)
			.filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
			.mapNotNull(this::toMessageWrapper)
//			.filter(Objects::isNull)
			.map(message -> {
				this.middlewares.forEach(middleware -> middleware.accept(message.getMessage()));
				return message;
			})
			.flatMap(this::handleCommand)
			.then();
	}

	protected void registerMiddleware(Consumer<Message> middleware) {
		middlewares.add(middleware);
	}

	protected void registerHandler(Command command, CommandHandler handler) {
		this.handlers.put(command, handler);
	}

	private MessageWrapper toMessageWrapper(@NotNull Message message) {
		if ("".contentEquals(message.getContent())) return null;

		var messageContent = message.getContent();
		var commandInput = removePrefix(messageContent.split("\s")[0]);

		if (!this.isValidCommand(commandInput))
			return null;

		return new MessageWrapper()
			.withMessage(message)
			.withCommand(Command.fromString(commandInput));
	}

	private boolean isValidCommand(String command) {
		return Arrays
			.stream(Command.values())
			.anyMatch(v -> v.getValue().contentEquals(command));
	}

	private String removePrefix(String str) {
		if (!str.startsWith(commandPrefix.toString())) return str;

		return str.substring(1);
	}

	private Mono<Void> handleCommand(MessageWrapper messageWrapper) {
		return this.handlers.get(messageWrapper.getCommand()).apply(messageWrapper.getMessage());
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@With
	private static class MessageWrapper {
		private Message message;
		private Command command;
	}
}


