package com.example.telegram_bot_practice.service.impls;

import com.example.telegram_bot_practice.config.ApplicationContextProvider;
import com.example.telegram_bot_practice.model.TelegramUser;
import com.example.telegram_bot_practice.service.TelegramBotNotifierService;
import com.example.telegram_bot_practice.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@EnableScheduling
@Service
public class TelegramBotNotifierServiceImpl implements TelegramBotNotifierService {

    @Autowired
    private TelegramUserService telegramUserService;

    @Scheduled(fixedRate = 10000)
    @Override
    public void notifyTelegramUsers() {
        List<TelegramUser> telegramUserList = telegramUserService.findAllTelegramUsersEnabledIs(true);
        String text = "some msg";
        for (TelegramUser telegramUser : telegramUserList) {
            long chatId = telegramUser.getId();
            SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
            Thread sendingThread = sendMessageInThread(sendMessage);
            sendingThread.start();
        }
    }

    private Thread sendMessageInThread(SendMessage sendMessage) {
        return new Thread(() -> {
            TelegramWebhookBot telegramWebhookBot = ApplicationContextProvider
                    .getApplicationContext().getBean(TelegramWebhookBot.class);
            try {
                telegramWebhookBot.execute(sendMessage);
                sendMessage.validate();
            } catch (TelegramApiException e) {
                System.out.println(e);
            }
        });
    }

}
