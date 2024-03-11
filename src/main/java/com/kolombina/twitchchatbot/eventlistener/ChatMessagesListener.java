package com.kolombina.twitchchatbot.eventlistener;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.time.ZonedDateTime;

public class ChatMessagesListener {
    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     */
    public ChatMessagesListener(SimpleEventHandler eventHandler, TwitchClient twitchClient) {
        eventHandler.onEvent(ChannelMessageEvent.class, event -> onChannelMessage(event, twitchClient));
    }

    private String kolombina = "───────────────────────██─ █──█─███────█──█─████─█──█ █──█─█──────█──█─█──█─█──█ ████─███────████─█──█─█─██ █──█─█──────█──█─█──█─██─█ █──█─███────█──█─████─█──█";
    private String ruckhunter = "⣿⣿⣿⣿⠏⢄⢖⢵⢝⡞⡮⡳⣍⠊⠥⠥⢑⣕⢎⢔⡈⡙⣿⣿⣿⣿⣿⣿ ⣿⣿⡟⢡⢪⡳⡝⢱⡫⣞⢝⡝⣎⢯⡳⡥⡠⡘⣝⢵⡱⡠⠘⣿⣿⣿⣿ ⣿⣿⢃⢕⢗⡍⢼⠵⡝⡎⠇⢙⣈⣊⠪⠳⣅⢗⡵⡳⣝⡜⡔⠘⣿⣿⣿⣿ ⣿⣿⠐⢭⡳⡅⡬⡯⡺⡨⡈⢿⣿⣿⣿⣦⠨⡳⣝⣝⢮⡊⡜⡀⣿⣿⣿⣿ ⣿⣿⣧⠑⣝⢮⡳⣝⢽⣸⢰⠠⠙⠟⡋⡡⣸⣚⠮⢊⣓⠁⡎⡂⣸⣿⣿⣿ ⣿⣿⣿⣧⡘⡜⡮⣳⢳⢥⠱⣝⢼⢤⢌⠚⢮⣢⡲⡳⡅⡜⡌⢰⣿⣿⣿⣿ ⣿⣿⣿⠟⢃⠈⡊⢗⡽⣕⢇⡐⢌⡊⣏⢯⡢⣔⠙⢝⠜⢈⣠⣿⣿⣿⣿⣿ ⣿⡿⢁⢎⢮⢝⣆⡂⡙⢼⢕⢯⡲⣜⢵⡫⣞⢵⡹⡠⢐⠻⣿⣿⣿⣿⣿⣿ ⡿⢁⢧⣫⡳⣝⢮⡺⣢⣂⠉⠳⡹⣪⡳⣝⢮⡳⢕⣇ ⣿⣿⣿ ⠃⠘⢮⢺⢜⡮⡳⡹⢐⣈⣬⣴⣌⡊⠞⡎⣗ВИЖУ ЛИШНЕГО ⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿⣷⣌ МОДЕРА ⠄⠁⠄⠄⡑⠈⡁⢋⠪⠣⠁⠈⢻⣿⣿⣿⣿ @ruckhunter ⣿";

    private String kurwa = "PeepoEvil Как-то днём бобёр Борис PeepoFeelsBobrMan под Еленой ветку сгрыз SquirrelJamDanceAnimal\u200B Воет белочка от боли: \"Kurwa Bober! Ja pierdole!\" WidePeepoHyperSpin";
    private ZonedDateTime timeoutKolombina = ZonedDateTime.now();
    private ZonedDateTime timeoutRuck = ZonedDateTime.now();
    private ZonedDateTime timeoutKurwa = ZonedDateTime.now();

    /**
     * Subscribe to the ChannelMessage Event and write the output to the console
     */
    public void onChannelMessage(ChannelMessageEvent event, TwitchClient twitchClient) {
        //todo что-то нормальное вместо switch/case - команд дальше будет больше
        switch (event.getMessage()) {
            case "!коломбина" -> {
//                if (Duration.between(timeoutKolombina, ZonedDateTime.now()).getSeconds() >= 30) {
                    twitchClient.getChat().sendMessage("nosferatu_old_one", kolombina);
                    timeoutKolombina = ZonedDateTime.now();
//                }
            }
            case "!рак" -> {
//                if (Duration.between(timeoutRuck, ZonedDateTime.now()).getSeconds() >= 30) {
                    twitchClient.getChat().sendMessage("nosferatu_old_one", ruckhunter);
                    timeoutRuck = ZonedDateTime.now();
//                }
            }
            case "!bobr" -> {
//                if (Duration.between(timeoutKurwa, ZonedDateTime.now()).getSeconds() >= 30) {
                    twitchClient.getChat().sendMessage("nosferatu_old_one", kurwa);
                    timeoutKurwa = ZonedDateTime.now();
//                }
            }
            default -> {
            }
        }
    }
}
