package com.light.bulb.chan.lightbulbchan.models;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MainMenuKeyboard extends ReplyKeyboardMarkup {
    public static final String WEATHER_BUTTON_TEXT = "Weather";
    public static final String FILES_BUTTON_TEXT = "Files";
    public static final String SCHEDULE_BUTTON_TEXT = "Schedule";

    public MainMenuKeyboard() {
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(0, new KeyboardButton().setText(WEATHER_BUTTON_TEXT).setRequestLocation(true));
        buttons.add(row);
        row = new KeyboardRow();
        row.add(0, new KeyboardButton().setText(FILES_BUTTON_TEXT));
        buttons.add(row);
        row = new KeyboardRow();
        row.add(0, new KeyboardButton().setText(SCHEDULE_BUTTON_TEXT));
        buttons.add(row);
        setKeyboard(buttons);
    }
}
