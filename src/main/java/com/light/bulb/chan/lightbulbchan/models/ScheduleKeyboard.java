package com.light.bulb.chan.lightbulbchan.models;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ScheduleKeyboard extends InlineKeyboardMarkup {
    private static final String[] days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};

    public ScheduleKeyboard() {
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>(){};
        for (int i = 0; i < 6; ++i) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            buttonList.add(new InlineKeyboardButton().setText(days[i]).setCallbackData(Integer.toString(i)));
            allButtons.add(buttonList);
        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        buttonList.add(new InlineKeyboardButton().setText("Ближайшее").setCallbackData(Integer.toString(6)));
        allButtons.add(buttonList);
        buttonList = new ArrayList<>();
        buttonList.add(new InlineKeyboardButton().setText("Поменять группу").setCallbackData(Integer.toString(-1)));
        allButtons.add(buttonList);
        setKeyboard(allButtons);
    }
}
