package de.stuchlyf.hungergamesdiscordgw.business.command.handler.impl;

import de.stuchlyf.hungergamesdiscordgw.business.command.Command;
import de.stuchlyf.hungergamesdiscordgw.business.command.handler.CommandHandler;
import de.stuchlyf.hungergamesdiscordgw.common.configuration.ConfigProperties;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.GuildChannel;
import discord4j.core.spec.CategoryCreateSpec;
import discord4j.core.spec.TextChannelCreateSpec;
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateBallotHandler implements CommandHandler {
	
	@Getter
	private final Command handlerFor;

	private final String categoryName;
	private final String roleName;

	private final PermissionSet ballotCategoryPermissionSet = PermissionSet.of(Permission.READ_MESSAGE_HISTORY, Permission.SEND_MESSAGES, Permission.VIEW_CHANNEL);

	@Autowired
	public CreateBallotHandler(ConfigProperties configProperties, RestTemplate restTemplate) {
		this.categoryName = configProperties.getBot().getHungerGamesCategoryName();
		this.roleName = configProperties.getBot().getHungerGamesRoleName();
		
		this.handlerFor = Command.CREATE_BALLOT;
	}

	@Override
	public Mono<Void> apply(Message message) {
		var textChannelTemplate = TextChannelCreateSpec.builder().name("ballot-%s".formatted(LocalDateTime.now().toString()));

		return message
			.getGuild()
			.flatMap(this::categoryExists)
			.flatMap(categoryExists -> this.createCategoryIfNotExists(message, categoryExists))
			.flatMap(category ->
				message
					.getGuild()
					.flatMap(g -> g.createTextChannel(textChannelTemplate.parentId(category.getId()).build()))
			)
			.then();
	}

	private Mono<Boolean> categoryExists(Guild guild) {
		return guild
			.getChannels()
			.any(channel -> this.categoryName.contentEquals(channel.getName()));
	}

	private Mono<GuildChannel> createCategoryIfNotExists(Message message, Boolean categoryExists) {
		if (Boolean.TRUE.equals(categoryExists)) {
			return message
				.getGuild()
				.flatMap(guild ->
					guild
						.getChannels()
						.filter(channel -> this.categoryName.contentEquals(channel.getName()))
						.single()
				);
		} else {
			return message
				.getGuild()
				.flatMap(guild ->
					guild
						.getRoles()
						.filter(role -> this.roleName.equals(role.getName()))
						.single()
				)
				.flatMap(role -> message
					.getGuild()
					.flatMap(guild -> {
						var ballotCategoryPermissionOverwrite = List.of(
							PermissionOverwrite.forRole(role.getId(), ballotCategoryPermissionSet, PermissionSet.of()),
							// TODO: replace .block() & find better way to get own role
							PermissionOverwrite.forRole(guild.getSelfMember().block().getRoles().filter(r -> "HungerGames".contentEquals(r.getName())).single().block().getId(), ballotCategoryPermissionSet, PermissionSet.of()),
							// TODO: replace .block()
							PermissionOverwrite.forRole(guild.getEveryoneRole().block().getId(), PermissionSet.of(), PermissionSet.of(Permission.VIEW_CHANNEL))
						);

						var ballotCategoryCreateSpec = CategoryCreateSpec
							.builder()
							.name(this.categoryName)
							.addAllPermissionOverwrites(ballotCategoryPermissionOverwrite)
							.build();

						return guild
							.createCategory(ballotCategoryCreateSpec);
					}));
		}
	}
}
