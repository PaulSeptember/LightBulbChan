package com.light.bulb.chan.lightbulbchan.models;

public class UserData {
    private String lat;
    private String lon;
    private String group;
    private Long chatId;

    public String getLat() {
        return lat;
    }

    public UserData setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLon() {
        return lon;
    }

    public UserData setLon(String lon) {
        this.lon = lon;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public UserData setGroup(String group) {
        this.group = group;
        return this;
    }

    public Long getChatId() {
        return chatId;
    }

    public UserData setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }
}
