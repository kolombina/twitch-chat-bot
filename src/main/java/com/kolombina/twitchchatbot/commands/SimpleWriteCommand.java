package com.kolombina.twitchchatbot.commands;

import com.kolombina.twitchchatbot.utils.Timer;

public class SimpleWriteCommand {

    /**
     * Текст, который будет выводиться на триггер
     */
    private String commandText;

    /**
     * Таймер команды
     */
    private Timer timer;

    public SimpleWriteCommand(String commandText, Timer timer) {
        this.commandText = commandText;
        this.timer = timer;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
