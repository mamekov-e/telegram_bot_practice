package com.example.telegram_bot_practice.config;

import com.example.telegram_bot_practice.telegram.SpringWebhookTelegramBot;
import com.example.telegram_bot_practice.telegram.TelegramFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class TelegramBotConfig {
    @Value("${telegram.bot.webHookPath}")
    private String botPath;
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;

    @Bean
    public SetWebhook setWebhook() {
        return SetWebhook.builder().url(botPath).build();
    }

    @Bean
    public SpringWebhookTelegramBot WebhookTelegramBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        SpringWebhookTelegramBot springWebhookTelegramBot = new SpringWebhookTelegramBot(telegramFacade, setWebhook, botToken);
        springWebhookTelegramBot.setBotPath(botPath);
        springWebhookTelegramBot.setBotUsername(botUsername);
        return springWebhookTelegramBot;
    }
}
