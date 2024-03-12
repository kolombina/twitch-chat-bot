package com.kolombina.twitchchatbot.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kolombina.twitchchatbot.utils.Timer;

import java.util.Objects;

public class SimpleWriteCommand {

    /**
     * Текст, который будет выводиться на триггер
     */
    private String commandText;

    /**
     * Таймер команды
     */
    private Timer timer;

    @JsonCreator
    public SimpleWriteCommand(@JsonProperty("commandText") String commandText,
                              @JsonProperty("timer") Timer timer) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleWriteCommand command = (SimpleWriteCommand) o;
        return Objects.equals(commandText, command.commandText) && Objects.equals(timer, command.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandText, timer);
    }

    @Override
    public String toString() {
        return "SimpleWriteCommand{" +
                "commandText='" + commandText + '\'' +
                ", timer=" + timer +
                '}';
    }
}
