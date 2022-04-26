package de.stuchlyf.hungergamesdiscordgw.business.command.handler;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface CommandHandler extends Function<Message, Mono<Void>> {
}
