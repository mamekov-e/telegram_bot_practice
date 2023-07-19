package com.example.telegram_bot_practice.service.impls;

import com.example.telegram_bot_practice.model.TelegramUser;
import com.example.telegram_bot_practice.repository.TelegramUserRepository;
import com.example.telegram_bot_practice.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Override
    public TelegramUser findById(long id) {
        return telegramUserRepository.findById(id)
                .orElse(null);
    }

    @Override
    public boolean existById(long id) {
        return findById(id) != null;
    }

    @Override
    public void saveTelegramUser(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public void deleteById(long id) {
        telegramUserRepository.deleteById(id);
    }

    @Override
    public List<TelegramUser> findAllTelegramUsersEnabledIs(boolean enable) {
        return telegramUserRepository.findAllByEnabledIs(enable);
    }
}
