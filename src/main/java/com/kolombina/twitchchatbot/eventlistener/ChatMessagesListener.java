package com.kolombina.twitchchatbot.eventlistener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.kolombina.twitchchatbot.commands.SimpleWriteCommand;
import com.kolombina.twitchchatbot.utils.Timer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChatMessagesListener {

    private String kolombina = "───────────────────────██─ █──█─███────█──█─████─█──█ █──█─█──────█──█─█──█─█──█ ████─███────████─█──█─█─██ █──█─█──────█──█─█──█─██─█ █──█─███────█──█─████─█──█";

    private String ruckhunter = "⣿⣿⣿⣿⠏⢄⢖⢵⢝⡞⡮⡳⣍⠊⠥⠥⢑⣕⢎⢔⡈⡙⣿⣿⣿⣿⣿⣿ ⣿⣿⡟⢡⢪⡳⡝⢱⡫⣞⢝⡝⣎⢯⡳⡥⡠⡘⣝⢵⡱⡠⠘⣿⣿⣿⣿ ⣿⣿⢃⢕⢗⡍⢼⠵⡝⡎⠇⢙⣈⣊⠪⠳⣅⢗⡵⡳⣝⡜⡔⠘⣿⣿⣿⣿ ⣿⣿⠐⢭⡳⡅⡬⡯⡺⡨⡈⢿⣿⣿⣿⣦⠨⡳⣝⣝⢮⡊⡜⡀⣿⣿⣿⣿ ⣿⣿⣧⠑⣝⢮⡳⣝⢽⣸⢰⠠⠙⠟⡋⡡⣸⣚⠮⢊⣓⠁⡎⡂⣸⣿⣿⣿ ⣿⣿⣿⣧⡘⡜⡮⣳⢳⢥⠱⣝⢼⢤⢌⠚⢮⣢⡲⡳⡅⡜⡌⢰⣿⣿⣿⣿ ⣿⣿⣿⠟⢃⠈⡊⢗⡽⣕⢇⡐⢌⡊⣏⢯⡢⣔⠙⢝⠜⢈⣠⣿⣿⣿⣿⣿ ⣿⡿⢁⢎⢮⢝⣆⡂⡙⢼⢕⢯⡲⣜⢵⡫⣞⢵⡹⡠⢐⠻⣿⣿⣿⣿⣿⣿ ⡿⢁⢧⣫⡳⣝⢮⡺⣢⣂⠉⠳⡹⣪⡳⣝⢮⡳⢕⣇ ⣿⣿⣿ ⠃⠘⢮⢺⢜⡮⡳⡹⢐⣈⣬⣴⣌⡊⠞⡎⣗ВИЖУ ЛИШНЕГО ⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿⣷⣌ МОДЕРА ⠄⠁⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿ @ruckhunter ⣿";
    private String kurwa = "PeepoEvil Как-то днём бобёр Борис PeepoFeelsBobrMan под Еленой ветку сгрыз SquirrelJamDanceAnimal\u200B Воет белочка от боли: \"Kurwa Bober! Ja pierdole!\" WidePeepoHyperSpin";

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

        this.mapOfCommands = new HashMap<>();
        mapOfCommands.put("!коломбина", new SimpleWriteCommand(kolombina, Timer.expired(Duration.ofSeconds(30))));
        mapOfCommands.put("!рак", new SimpleWriteCommand(ruckhunter, Timer.expired(Duration.ofSeconds(30))));
        mapOfCommands.put("!bobr", new SimpleWriteCommand(kurwa, Timer.expired(Duration.ofSeconds(30))));

        writeCommands(mapOfCommands);
    }

    /**
     * Subscribe to the ChannelMessage Event and write the output to the console
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

    private void writeCommands(Map<String, SimpleWriteCommand> mapOfCommands) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String mapOfCommandsString = objectMapper.writeValueAsString(mapOfCommands);
            System.out.println(mapOfCommandsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
