package com.kolombina.twitchchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TwitchChatBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitchChatBotApplication.class, args);
        System.out.println("Program started");
    }

    //todo
    // продумать структуру проекта
    // фронт, на котором можно будет добавлять/редактировать команды
    // ChatMessagesListener chatMessagesListener = new ChatMessagesListener(eventHandler, twitchClient); - сделать красиво, а не просто создать объект без ничего?
    // научиться получать список юзеров онлайн на канале

}
