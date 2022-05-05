package de.stuchlyf.hungergames.discordgw.common.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("discord")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigProperties {
	
	private Bot bot = new Bot();
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Bot {
		
		private String token;
		private Character commandPrefix;
		private String hungerGamesCategoryName;
		private String hungerGamesRoleName;
	}
}
