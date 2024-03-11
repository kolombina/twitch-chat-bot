package com.kolombina.twitchchatbot.eventlistener;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.kolombina.twitchchatbot.utils.Timer;

import java.time.Duration;

public class ChatMessagesListener {
    private String kolombina = "───────────────────────██─ █──█─███────█──█─████─█──█ █──█─█──────█──█─█──█─█──█ ████─███────████─█──█─█─██ █──█─█──────█──█─█──█─██─█ █──█─███────█──█─████─█──█";

    private String ruckhunter = "⣿⣿⣿⣿⠏⢄⢖⢵⢝⡞⡮⡳⣍⠊⠥⠥⢑⣕⢎⢔⡈⡙⣿⣿⣿⣿⣿⣿ ⣿⣿⡟⢡⢪⡳⡝⢱⡫⣞⢝⡝⣎⢯⡳⡥⡠⡘⣝⢵⡱⡠⠘⣿⣿⣿⣿ ⣿⣿⢃⢕⢗⡍⢼⠵⡝⡎⠇⢙⣈⣊⠪⠳⣅⢗⡵⡳⣝⡜⡔⠘⣿⣿⣿⣿ ⣿⣿⠐⢭⡳⡅⡬⡯⡺⡨⡈⢿⣿⣿⣿⣦⠨⡳⣝⣝⢮⡊⡜⡀⣿⣿⣿⣿ ⣿⣿⣧⠑⣝⢮⡳⣝⢽⣸⢰⠠⠙⠟⡋⡡⣸⣚⠮⢊⣓⠁⡎⡂⣸⣿⣿⣿ ⣿⣿⣿⣧⡘⡜⡮⣳⢳⢥⠱⣝⢼⢤⢌⠚⢮⣢⡲⡳⡅⡜⡌⢰⣿⣿⣿⣿ ⣿⣿⣿⠟⢃⠈⡊⢗⡽⣕⢇⡐⢌⡊⣏⢯⡢⣔⠙⢝⠜⢈⣠⣿⣿⣿⣿⣿ ⣿⡿⢁⢎⢮⢝⣆⡂⡙⢼⢕⢯⡲⣜⢵⡫⣞⢵⡹⡠⢐⠻⣿⣿⣿⣿⣿⣿ ⡿⢁⢧⣫⡳⣝⢮⡺⣢⣂⠉⠳⡹⣪⡳⣝⢮⡳⢕⣇ ⣿⣿⣿ ⠃⠘⢮⢺⢜⡮⡳⡹⢐⣈⣬⣴⣌⡊⠞⡎⣗ВИЖУ ЛИШНЕГО ⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿⣷⣌ МОДЕРА ⠄⠁⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿ @ruckhunter ⣿";
    private String kurwa = "PeepoEvil Как-то днём бобёр Борис PeepoFeelsBobrMan под Еленой ветку сгрыз SquirrelJamDanceAnimal\u200B Воет белочка от боли: \"Kurwa Bober! Ja pierdole!\" WidePeepoHyperSpin";

    private Timer kolombinaTimer;
    private Timer ruckTimer;
    private Timer kurwaTimer;

    private String channelName;

    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     */
    public ChatMessagesListener(SimpleEventHandler eventHandler, TwitchClient twitchClient, String channelName) {
        eventHandler.onEvent(ChannelMessageEvent.class, event -> onChannelMessage(event, twitchClient));
        this.channelName = channelName;
        this.kolombinaTimer = Timer.expired(Duration.ofSeconds(30));
        this.ruckTimer = Timer.expired(Duration.ofSeconds(30));
        this.kurwaTimer = Timer.expired(Duration.ofSeconds(30));
    }

    /**
     * Subscribe to the ChannelMessage Event and write the output to the console
     */
    public void onChannelMessage(ChannelMessageEvent event, TwitchClient twitchClient) {
        //todo что-то нормальное вместо switch/case - команд дальше будет больше
        switch (event.getMessage()) {
            case "!коломбина" -> {
                if (kolombinaTimer.isExpired()) {
                    twitchClient.getChat().sendMessage(channelName, kolombina);
                    kolombinaTimer.restart();
                }
            }
            case "!рак" -> {
                if (ruckTimer.isExpired()) {
                    twitchClient.getChat().sendMessage(channelName, ruckhunter);
                    ruckTimer.restart();
                }
            }
            case "!bobr" -> {
                if (kurwaTimer.isExpired()) {
                    twitchClient.getChat().sendMessage(channelName, kurwa);
                    kurwaTimer.restart();
                }
            }
            default -> {
            }
        }
    }
}
