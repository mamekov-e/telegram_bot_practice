package com.example.telegram_bot_practice.repository;

import com.example.telegram_bot_practice.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    List<TelegramUser> findAllByEnabledIs(boolean enabled);

}
