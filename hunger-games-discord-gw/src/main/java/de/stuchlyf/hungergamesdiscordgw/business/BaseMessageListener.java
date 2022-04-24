package de.stuchlyf.hungergamesdiscordgw.business;


import de.stuchlyf.hungergamesdiscordgw.business.command.Command;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import lombok.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseMessageListener {
	
	@Getter
	private final List<Consumer<Message>> middlewares;
	@Getter
	private final Map<Command, Function<Message, Mono<Void>>> handlers;
	
	@Getter 
	private final Character commandPrefix;
	
	protected BaseMessageListener(Character commandPrefix) {
		this.commandPrefix = commandPrefix;
		this.middlewares = new ArrayList<>();
		this.handlers = new EnumMap<>(Command.class);
		
		Function<Message, Mono<Void>> voteHandler = message ->
			message
				.getChannel()
				.flatMap(channel -> channel.createMessage("ich hab deine mom gebangt"))
				.then();
	
		
		this.registerHandler(Command.VOTE, voteHandler);
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
	
	protected void registerHandler(Command command, Function<Message, Mono<Void>> handler) {
		this.handlers.put(command, handler);
	}
	
	private MessageWrapper toMessageWrapper(@NotNull Message message) {
		if("".contentEquals(message.getContent())) return null;
		
		var messageContent = message.getContent();
		var commandInput = removePrefix(messageContent.split("\s")[0]);
		
		if (!this.isValidCommand(commandInput)) 
			return null;
		
		return new MessageWrapper()
			.withMessage(message)
			.withCommand(Command.valueOf(commandInput));
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


