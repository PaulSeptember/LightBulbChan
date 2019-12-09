package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Core implements MessageReceiver{
    @Autowired
    private TelegramGateway gateway;
    @Autowired
    private Weather weather;
    @Autowired
    private ScheduleManager scheduleManager;
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
                gateway.sendMessage(ScheduleManager.parseSchedule(response.getValue()), message.getChatId());
            }
        }
    }
}
