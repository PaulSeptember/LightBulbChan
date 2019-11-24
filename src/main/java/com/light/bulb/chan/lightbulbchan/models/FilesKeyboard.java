package com.light.bulb.chan.lightbulbchan.models;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class FilesKeyboard extends InlineKeyboardMarkup {
    public static final String[] EXT = {".mp3", ".txt", ".pdf"};

    public FilesKeyboard() {
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            buttonList.add(new InlineKeyboardButton().setText("Все " + EXT[i]).setCallbackData(EXT[i]));
            allButtons.add(buttonList);
        }

        setKeyboard(allButtons);
    }
}
