package de.stuchlyf.hungergamesdiscordgw.business;

import de.stuchlyf.hungergamesdiscordgw.business.command.handler.CommandHandler;
import de.stuchlyf.hungergamesdiscordgw.common.configuration.ConfigProperties;
import de.stuchlyf.hungergamesdiscordgw.common.event.EventListener;
import discord4j.core.event.domain.message.MessageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MessageUpdateListener extends BaseMessageListener implements EventListener<MessageUpdateEvent> {
	
	@Autowired
	protected MessageUpdateListener(ConfigProperties configProperties, List<CommandHandler> commandHandlers) {
		super(configProperties.getBot().getCommandPrefix(), commandHandlers);
	}

	@Override
	public Class<MessageUpdateEvent> getEventType() {
		return MessageUpdateEvent.class;
	}

	@Override
	public Mono<Void> execute(MessageUpdateEvent event) {
		return Mono.just(event)
			.filter(MessageUpdateEvent::isContentChanged)
			.flatMap(MessageUpdateEvent::getMessage)
			.flatMap(super::processCommand);
	}
}
