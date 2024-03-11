package com.kolombina.twitchchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Configuration.class)
public class TwitchChatBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitchChatBotApplication.class, args);
    }

    //todo
    // продумать структуру проекта
    // switch-case заменить на что-то более адекватное - по ивент листенеру на каждую команду?
    // ChatMessagesListener chatMessagesListener = new ChatMessagesListener(eventHandler, twitchClient); - сделать красиво, а не просто создать объект без ничего?
    // научиться получать список юзеров онлайн на канале
    // добавить логгер хотя бы для "бот стартовал"

}
