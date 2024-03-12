package com.kolombina.twitchchatbot.eventlistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.kolombina.twitchchatbot.commands.SimpleWriteCommand;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatMessagesListener {

    private final String channelName;

    private final Map<String, SimpleWriteCommand> mapOfCommands;

    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     */
    public ChatMessagesListener(SimpleEventHandler eventHandler, TwitchClient twitchClient, String channelName) {
        eventHandler.onEvent(ChannelMessageEvent.class, event -> onChannelMessage(event, twitchClient));
        this.channelName = channelName;
        this.mapOfCommands = readCommands();
    }

    /**
     * Subscribe to the ChannelMessage Event
     */
    public void onChannelMessage(ChannelMessageEvent event, TwitchClient twitchClient) {
        if (mapOfCommands.containsKey(event.getMessage())) {
            SimpleWriteCommand command = mapOfCommands.get(event.getMessage());

            if (command.getTimer().isExpired()) {
                twitchClient.getChat().sendMessage(channelName, command.getCommandText());
                command.getTimer().restart();
            }
        }
    }

    private Map<String, SimpleWriteCommand> readCommands() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeReference<HashMap<String, SimpleWriteCommand>> typeRef = new TypeReference<>() {
            };
            return objectMapper.readValue(new File("src/main/resources/commands.json"), typeRef);
        } catch (JsonProcessingException e) {
            System.out.println("Error during reading commands.json");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Can't find file");
            throw new RuntimeException(e);
        }

    }
}
