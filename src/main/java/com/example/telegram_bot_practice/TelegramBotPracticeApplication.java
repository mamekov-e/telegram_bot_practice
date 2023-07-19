package com.example.telegram_bot_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@Import({TelegramBotStarterConfiguration.class})
@SpringBootApplication
public class TelegramBotPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotPracticeApplication.class, args);
    }

}
