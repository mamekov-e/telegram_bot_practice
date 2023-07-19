package com.example.telegram_bot_practice.telegram.handler;

import com.example.telegram_bot_practice.model.TelegramUser;
import com.example.telegram_bot_practice.service.TelegramUserService;
import com.example.telegram_bot_practice.telegram.component.MenuOptionComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class EventHandler {

    @Autowired
    private TelegramUserService telegramUserService;
    @Autowired
    private MenuOptionComponent menuOptionComponent;

    public SendMessage addTelegramUser(long chatId, String username, SendMessage sendMessage) {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setId(chatId);
        telegramUser.setUsername(username);
        telegramUser.setEnabled(false);

        telegramUserService.saveTelegramUser(telegramUser);

        String greetingText = String.format("Добро пожаловать, %s!\n" +
                        "Я - бот сервиса Site Health Tracker. " +
                        "В мои обязанности входит оповещать подписчиков при изменении статусов групп.\n\n" +
                        "Ваш аккаунт успешно добавлен в наш сервис. " +
                        "Для получения уведомлений об изменениях статусов групп выберите подписаться в меню.",
                username);
        sendMessage.setText(greetingText);

        return sendMessage;
    }

    public SendMessage helpMessage(SendMessage sendMessage) {
        String anyText = "Доступные опции меню (можно ввести номер):\n" +
                "1. Подписаться\n" +
                "2. Отписаться\n" +
                "3. Помощь\n";
        sendMessage.setText(anyText);
        return sendMessage;
    }

    public BotApiMethod<?> changeEnabledTo(long chatId, boolean enabled) {
        TelegramUser telegramUser = telegramUserService.findById(chatId);
        if (telegramUser.isEnabled() != enabled) {
            telegramUser.setEnabled(enabled);
            telegramUserService.saveTelegramUser(telegramUser);

            String statusChangedText = enabled ? "Вы успешно подписались на рассылку" :
                    "Вы успешно отписались от рассылки";
            return menuOptionComponent.showMenuOptions(statusChangedText, chatId);
        } else {
            String statusAlreadyChangedText = enabled ? "Вы уже подписаны на рассылку" :
                    "Вы уже отписаны от рассылки";
            return menuOptionComponent.showMenuOptions(statusAlreadyChangedText, chatId);
        }
    }
}
