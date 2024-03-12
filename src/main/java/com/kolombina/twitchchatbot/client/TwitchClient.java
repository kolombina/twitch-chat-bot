package com.kolombina.twitchchatbot.client;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClientBuilder;
import com.kolombina.twitchchatbot.configuration.CommonConfiguration;
import com.kolombina.twitchchatbot.eventlistener.ChatMessagesListener;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitchClient {

    @Autowired
    CommonConfiguration commonConfiguration;

    @PostConstruct
    public void init() {
        // chat credential
        //note: if you use https://twitchtokengenerator.com/ (and their client id), your token does not expire
        OAuth2Credential credential = new OAuth2Credential("twitch", commonConfiguration.getAccessToken());

        com.github.twitch4j.TwitchClient twitchClient = TwitchClientBuilder.builder()
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
                .withClientId(commonConfiguration.getClientId())
                .build();

        //получаем eventHandler
        SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
        //создаем листенер ивента
        ChatMessagesListener chatMessagesListener = new ChatMessagesListener(eventHandler, twitchClient, commonConfiguration.getChannelName());
        //определяем, на каком канале работает бот
        twitchClient.getChat().joinChannel(commonConfiguration.getChannelName());
        twitchClient.getClientHelper().enableStreamEventListener(commonConfiguration.getChannelName());
    }
}
