package com.kolombina.twitchchatbot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "commandslist")
public class CommandsListConfiguration {
    private Map<String, String> commandslist = new HashMap<>();

    public void setCommandslist(Map<String, String> commandslist) {
        this.commandslist = commandslist;
    }

    public Map<String, String> getCommandsList() {
        return commandslist;
    }
}
