package de.stuchlyf.hungergamesdiscordgw.common.client;

import de.stuchlyf.hungergamesdiscordgw.common.configuration.ConfigProperties;
import de.stuchlyf.hungergamesdiscordgw.common.event.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Component
public class GatewayDiscordClientWrapper<T extends Event> {
	private final GatewayDiscordClient client;
	
	@Autowired
	public GatewayDiscordClientWrapper(ConfigProperties configProperties, List<EventListener<T>> listeners) {
		this.client = DiscordClientBuilder
			.create(configProperties.getBot().getToken())
			.build()
			.login()
			.block();
		
		listeners.forEach(listener -> {
			log.debug("Registering Listener for Event: {}", listener.getEventType());
			this.client.on(listener.getEventType())
				.flatMap(listener::execute)
				.onErrorResume(listener::handleError)
				.subscribe();
		});
	}
	
	@PreDestroy
	private void preDestroy() {
		this.client
			.logout()
			.block();
	}
}
