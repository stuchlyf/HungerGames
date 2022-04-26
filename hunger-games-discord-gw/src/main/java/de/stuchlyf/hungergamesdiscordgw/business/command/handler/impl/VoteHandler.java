package de.stuchlyf.hungergamesdiscordgw.business.command.handler.impl;

import de.stuchlyf.hungergamesdiscordgw.business.command.handler.CommandHandler;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public class VoteHandler implements CommandHandler {


	@Override
	public Mono<Void> apply(Message message) {
		return message
			.getChannel()
			.flatMap(channel -> channel.createMessage("ich hab deine mom gebangt"))
			.then();
	}
}
