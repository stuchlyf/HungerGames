package de.stuchlyf.hungergames.discordgw;

import de.stuchlyf.hungergames.discordgw.common.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class HungerGamesDiscordGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(HungerGamesDiscordGwApplication.class, args);
	}

}
