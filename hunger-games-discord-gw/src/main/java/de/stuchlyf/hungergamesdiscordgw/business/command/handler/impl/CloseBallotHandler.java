package de.stuchlyf.hungergamesdiscordgw.business.command.handler.impl;

import de.stuchlyf.hungergamesdiscordgw.business.command.Command;
import de.stuchlyf.hungergamesdiscordgw.business.command.handler.CommandHandler;
import discord4j.core.object.entity.Message;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CloseBallotHandler implements CommandHandler {
	
	@Getter
	private final Command handlerFor;
	
	public CloseBallotHandler() {
		this.handlerFor = Command.CLOSE_BALLOT;
	}
	
	@Override
	public Mono<Void> apply(Message message) {
		return message
			.getChannel()
			.flatMap(channel -> channel.createMessage("Ballot Closed!"))
			.then();
	}
}
