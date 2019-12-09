package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.LBCMessage;
import com.light.bulb.chan.lightbulbchan.models.LBCMessageType;
import com.light.bulb.chan.lightbulbchan.models.MainMenuKeyboard;
import com.light.bulb.chan.lightbulbchan.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.*;

@Service
public class TelegramGateway extends TelegramLongPollingBot {
    private static final Logger log = LogManager.getLogger(TelegramGateway.class);
    private static TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    private final BotSettings settings;
    private final Map<Long, String> chatToGroup = new HashMap<>();

    @Autowired
    private Core core;

    public TelegramGateway(BotSettings settings) throws TelegramApiRequestException {
        this.settings = settings;
        telegramBotsApi.registerBot(this);
    }

    public TelegramGateway() throws TelegramApiRequestException {
        this(new BotSettings().setName("LightBulbChanBot").setToken(""));
    }

    public Response sendMessage(final String text, final Long chatId) {
        SendMessage sendMessageRequest = new SendMessage().setText(text).setChatId(chatId).setReplyMarkup(new MainMenuKeyboard());
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.error("Cannot send message. Text: " + text + " ChatId: " + chatId);
            new Response(null, false);
        }

        return new Response(null, true);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.getLocation() != null) {
                Location location = message.getLocation();
                core.OnMessage(new LBCMessage().setType(LBCMessageType.WEATHER)
                        .setLat(Float.toString(location.getLatitude())).setLon(Float.toString(location.getLongitude())).setChatId(message.getChatId()));
            } else if (message.getText().equals(MainMenuKeyboard.SCHEDULE_BUTTON_TEXT)) {
                if (!chatToGroup.containsKey(message.getChatId())) {
                    sendMessage("Введи свою группу пожалуйста :)", message.getChatId());
                } else {
                    core.OnMessage(new LBCMessage().setType(LBCMessageType.SCHEDULE).setChatId(message.getChatId())
                            .setGroupId(chatToGroup.get(message.getChatId())));
                }
            } else if (message.getText().length() == 6) {
                chatToGroup.put(message.getChatId(), message.getText());
                core.OnMessage(new LBCMessage().setType(LBCMessageType.SCHEDULE).setChatId(message.getChatId())
                        .setGroupId(chatToGroup.get(message.getChatId())));
            } else {
                sendMessage("Используйте меню для ввода!", message.getChatId());
            }
        }
    }


    @Override
    public String getBotUsername() {
        return settings.getName();
    }

    @Override
    public String getBotToken() {
        return settings.getToken();
    }
}
