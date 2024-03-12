package com.kolombina.twitchchatbot.eventlistener;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.kolombina.twitchchatbot.commands.SimpleWriteCommand;
import com.kolombina.twitchchatbot.configuration.CommandsListConfiguration;
import com.kolombina.twitchchatbot.utils.Timer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChatMessagesListener {

    private CommandsListConfiguration commandsListConfiguration;
    private String kolombina = "───────────────────────██─ █──█─███────█──█─████─█──█ █──█─█──────█──█─█──█─█──█ ████─███────████─█──█─█─██ █──█─█──────█──█─█──█─██─█ █──█─███────█──█─████─█──█";

    private String ruckhunter = "⣿⣿⣿⣿⠏⢄⢖⢵⢝⡞⡮⡳⣍⠊⠥⠥⢑⣕⢎⢔⡈⡙⣿⣿⣿⣿⣿⣿ ⣿⣿⡟⢡⢪⡳⡝⢱⡫⣞⢝⡝⣎⢯⡳⡥⡠⡘⣝⢵⡱⡠⠘⣿⣿⣿⣿ ⣿⣿⢃⢕⢗⡍⢼⠵⡝⡎⠇⢙⣈⣊⠪⠳⣅⢗⡵⡳⣝⡜⡔⠘⣿⣿⣿⣿ ⣿⣿⠐⢭⡳⡅⡬⡯⡺⡨⡈⢿⣿⣿⣿⣦⠨⡳⣝⣝⢮⡊⡜⡀⣿⣿⣿⣿ ⣿⣿⣧⠑⣝⢮⡳⣝⢽⣸⢰⠠⠙⠟⡋⡡⣸⣚⠮⢊⣓⠁⡎⡂⣸⣿⣿⣿ ⣿⣿⣿⣧⡘⡜⡮⣳⢳⢥⠱⣝⢼⢤⢌⠚⢮⣢⡲⡳⡅⡜⡌⢰⣿⣿⣿⣿ ⣿⣿⣿⠟⢃⠈⡊⢗⡽⣕⢇⡐⢌⡊⣏⢯⡢⣔⠙⢝⠜⢈⣠⣿⣿⣿⣿⣿ ⣿⡿⢁⢎⢮⢝⣆⡂⡙⢼⢕⢯⡲⣜⢵⡫⣞⢵⡹⡠⢐⠻⣿⣿⣿⣿⣿⣿ ⡿⢁⢧⣫⡳⣝⢮⡺⣢⣂⠉⠳⡹⣪⡳⣝⢮⡳⢕⣇ ⣿⣿⣿ ⠃⠘⢮⢺⢜⡮⡳⡹⢐⣈⣬⣴⣌⡊⠞⡎⣗ВИЖУ ЛИШНЕГО ⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿⣷⣌ МОДЕРА ⠄⠁⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿ @ruckhunter ⣿";
    private String kurwa = "PeepoEvil Как-то днём бобёр Борис PeepoFeelsBobrMan под Еленой ветку сгрыз SquirrelJamDanceAnimal\u200B Воет белочка от боли: \"Kurwa Bober! Ja pierdole!\" WidePeepoHyperSpin";

    private String channelName;

    private Map<String, SimpleWriteCommand> mapOfCommands;

    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     */
    public ChatMessagesListener(CommandsListConfiguration commandsListConfiguration, SimpleEventHandler eventHandler, TwitchClient twitchClient, String channelName) {
        eventHandler.onEvent(ChannelMessageEvent.class, event -> onChannelMessage(event, twitchClient));
        //todo научиться из application брать конфиги мапой
        this.commandsListConfiguration = commandsListConfiguration;
        this.channelName = channelName;

        this.mapOfCommands = new HashMap<>();
        mapOfCommands.put("!коломбина", new SimpleWriteCommand(kolombina, Timer.expired(Duration.ofSeconds(30))));
        mapOfCommands.put("!рак", new SimpleWriteCommand(ruckhunter, Timer.expired(Duration.ofSeconds(30))));
        mapOfCommands.put("!bobr", new SimpleWriteCommand(kurwa, Timer.expired(Duration.ofSeconds(30))));
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
}
