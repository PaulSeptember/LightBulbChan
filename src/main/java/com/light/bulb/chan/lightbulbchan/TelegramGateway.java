package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramGateway extends TelegramLongPollingBot {
    private static final Logger log = LogManager.getLogger(TelegramGateway.class);
    private static TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    private final BotSettings settings;
    private final Map<Long, String> chatToGroup = new HashMap<>();

    @Autowired
    private Core core;

    @Autowired
    public TelegramGateway(BotSettings settings) throws TelegramApiRequestException {
        this.settings = settings;
        telegramBotsApi.registerBot(this);
    }

    public Response sendMessage(final String text, final Long chatId, final ReplyKeyboard keyboard) {
        SendMessage sendMessageRequest = new SendMessage().setText(text).setChatId(chatId).setReplyMarkup(keyboard);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.error("Cannot send message. Text: " + text + " ChatId: " + chatId);
            new Response(null, false);
        }

        return new Response(null, true);
    }

    public Response sendMessage(final String text, final Long chatId) {
        return sendMessage(text, chatId, new MainMenuKeyboard());
    }

    public Response sendFile(final Long chatId, final File file) {
        SendDocument sendDocument = new SendDocument().setChatId(chatId).setDocument(file);
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.error("Cannot send file. ChatId: " + chatId);
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
                    sendMessage("Выбери опцию", message.getChatId(), new ScheduleKeyboard());
                }
            } else if (message.getText().equals(MainMenuKeyboard.FILES_BUTTON_TEXT)) {
                sendMessage("Выбери формат", message.getChatId(), new FilesKeyboard());
            } else if (message.getText().length() == 6) {
                chatToGroup.put(message.getChatId(), message.getText());
                core.OnMessage(new LBCMessage().setType(LBCMessageType.SCHEDULE).setChatId(message.getChatId())
                        .setGroupId(chatToGroup.get(message.getChatId())));
            } else {
                sendMessage("Используйте меню для ввода!", message.getChatId());
            }
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            for (String ext : FilesKeyboard.EXT) {
                if (ext.equals(callbackQuery.getData())) {
                    core.OnMessage(new LBCMessage().setType(LBCMessageType.FILES).setChatId(message.getChatId()).setExpansion(ext));
                    return;
                }
            }
            int day = Integer.parseInt(callbackQuery.getData());
            if (day == -1) {
                sendMessage("Введи свою группу пожалуйста :)", message.getChatId());
                return;
            }
            core.OnMessage(new LBCMessage().setType(LBCMessageType.SCHEDULE).setChatId(message.getChatId())
                    .setGroupId(chatToGroup.get(message.getChatId())).setDay(day));
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
