package com.kolombina.twitchchatbot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "twitchaccount")
public class CommonConfiguration {
    private String accessToken;
    private String clientId;
    private String channelName;

    public CommonConfiguration() {
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    public String getChannelName() {
        return channelName;
    }
}
