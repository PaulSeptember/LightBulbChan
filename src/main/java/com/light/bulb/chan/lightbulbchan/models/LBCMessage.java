package com.light.bulb.chan.lightbulbchan.models;

public class LBCMessage {
    private LBCMessageType type;
    private String data;
    private String lat;
    private String lon;
    private Long chatId;
    private String groupId;

    public LBCMessageType getType() {
        return type;
    }

    public LBCMessage setType(LBCMessageType type) {
        this.type = type;
        return this;
    }

    public String getData() {
        return data;
    }

    public LBCMessage setData(String data) {
        this.data = data;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public LBCMessage setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLon() {
        return lon;
    }

    public LBCMessage setLon(String lon) {
        this.lon = lon;
        return this;
    }

    public Long getChatId() {
        return chatId;
    }

    public LBCMessage setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public LBCMessage setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
