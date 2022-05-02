package de.stuchlyf.hungergamesdiscordgw.business;

import de.stuchlyf.hungergamesdiscordgw.business.command.handler.CommandHandler;
import de.stuchlyf.hungergamesdiscordgw.common.configuration.ConfigProperties;
import de.stuchlyf.hungergamesdiscordgw.common.event.EventListener;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
public class MessageCreateListener extends BaseMessageListener implements EventListener<MessageCreateEvent> {

	@Autowired
	public MessageCreateListener(ConfigProperties configProperties, List<CommandHandler> commandHandlers) {
		super(configProperties.getBot().getCommandPrefix(), commandHandlers);
		
		Consumer<Message> loggingMiddleware = (Message message) -> {
			var userId = message.getUserData().id().asString();
			var messageId = message.getId().asString();
			
			log.info("Received Message with ID {} by User with ID {} with Command {}", messageId, userId, message.getContent());
		};
		
		this.registerMiddleware(loggingMiddleware);
	}
	
	@Override
	public Class<MessageCreateEvent> getEventType() {
		return MessageCreateEvent.class;
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return processCommand(event.getMessage());
	}
}
