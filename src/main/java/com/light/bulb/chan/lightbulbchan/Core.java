package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Core implements MessageReceiver {
    @Autowired
    private TelegramGateway gateway;
    @Autowired
    private Weather weather;
    @Autowired
    private ScheduleManager scheduleManager;
    @Autowired
    private Files files;

    @Override
    public void OnMessage(LBCMessage message) {
        if (message.getType() == LBCMessageType.WEATHER) {
            Response<WeatherResponse> response = weather.getWeather(message.getLon(), message.getLat());
            if (response.isStatus()) {
                gateway.sendMessage(Weather.parseWeather(response.getValue()), message.getChatId());
            }
        }

        if (message.getType() == LBCMessageType.SCHEDULE) {
            Response<FullSchedule> response = scheduleManager.getSchedule(message.getGroupId());
            if (response.isStatus()) {
                if (message.getDay() > 5) {
                    gateway.sendMessage(ScheduleManager.parseSchedule(response.getValue()), message.getChatId());
                } else {
                    gateway.sendMessage(ScheduleManager.parseForDay(response.getValue(), message.getDay()), message.getChatId());
                }
            }
        }

        if (message.getType() == LBCMessageType.FILES) {
            for (File file : files.getAllWithExpansion(message.getExpansion())) {
                gateway.sendFile(message.getChatId(), file);
            }
        }
    }
}
