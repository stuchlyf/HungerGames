package de.stuchlyf.hungergames.discordgw.business.command.handler.impl;

import de.stuchlyf.hungergames.discordgw.business.command.Command;
import de.stuchlyf.hungergames.discordgw.business.command.handler.CommandHandler;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VoteHandler implements CommandHandler {

	@Getter
	private final Command handlerFor;
	
	public VoteHandler() {
		this.handlerFor = Command.VOTE;
	}

	@Override
	public Mono<Void> apply(Message message) {
		return message
			.getChannel()
			.flatMap(channel -> channel.createMessage("ich hab deine mom gebangt"))
			.then();
	}
}
