package com.kolombina.twitchchatbot;

import com.kolombina.twitchchatbot.test.TwitchClientImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Configuration.class)
public class TwitchChatBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(TwitchChatBotApplication.class, args);
		TwitchClientImpl.configureBot();
	}

	//todo
	// сделать файл-конфигурацию
	// проект залить в гит
	// по классам по логике все разнести нормально
	// нормальный таймер сделать
	// научиться получать список юзеров онлайн на канале

}
