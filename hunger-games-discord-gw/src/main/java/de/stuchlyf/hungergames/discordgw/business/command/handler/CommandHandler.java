package de.stuchlyf.hungergames.discordgw.business.command.handler;

import de.stuchlyf.hungergames.discordgw.business.command.Command;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface CommandHandler extends Function<Message, Mono<Void>> {
	Command getHandlerFor();
}
