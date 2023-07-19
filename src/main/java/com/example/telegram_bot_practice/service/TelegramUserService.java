package com.example.telegram_bot_practice.service;

import com.example.telegram_bot_practice.model.TelegramUser;

import java.util.List;

public interface TelegramUserService {

    TelegramUser findById(long id);

    boolean existById(long id);

    void saveTelegramUser(TelegramUser telegramUser);

    List<TelegramUser> findAllTelegramUsersEnabledIs(boolean enable);

    void deleteById(long userId);
}
