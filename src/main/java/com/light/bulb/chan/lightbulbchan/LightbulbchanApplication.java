package com.light.bulb.chan.lightbulbchan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class LightbulbchanApplication {
	static {
		ApiContextInitializer.init();
	}
	public static void main(String[] args) throws TelegramApiRequestException {
		SpringApplication.run(LightbulbchanApplication.class, args);
	}

}
