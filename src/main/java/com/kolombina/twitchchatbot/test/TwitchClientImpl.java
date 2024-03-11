package com.kolombina.twitchchatbot.test;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.kolombina.twitchchatbot.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitchClientImpl {

    @Autowired
    static
    Configuration configuration;
    public static void configureBot() {
        // chat credential
        //note: if you use https://twitchtokengenerator.com/ (and their client id), your token does not expire
        OAuth2Credential credential = new OAuth2Credential("twitch", configuration.getAccessToken());

        TwitchClient twitchClient = TwitchClientBuilder.builder()
                //helix - https://twitch4j.github.io/getting-started/client-helper
                .withEnableHelix(true)
                //авторизация
                .withDefaultAuthToken(credential)
                //подключаем модуль чата
                .withEnableChat(true)
                //даем OAuth для работы с чатом
                .withChatAccount(credential)
                //event listener
                .withDefaultEventHandler(SimpleEventHandler.class)
                .withClientId(configuration.getClientId())
                .build();

        //получаем eventHandler
        SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
        //создаем листенер ивента
        WriteChannelChatToConsole writeChannelChatToConsole = new WriteChannelChatToConsole(eventHandler, twitchClient);
        //определяем, на каком канале работает бот
        twitchClient.getChat().joinChannel(configuration.getChannelName());
        twitchClient.getClientHelper().enableStreamEventListener(configuration.getChannelName());
    }


}
