package com.light.bulb.chan.lightbulbchan;

public class BotSettings {
    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public BotSettings setName(String name) {
        this.name = name;
        return this;
    }

    public String getToken() {
        return token;
    }

    public BotSettings setToken(String token) {
        this.token = token;
        return this;
    }
}
