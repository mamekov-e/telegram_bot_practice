//package com.example.telegram_bot_practice.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//@Component
//public class LongPollingTelegramBot extends TelegramLongPollingBot {
//
//    private final String username;
//
//    public LongPollingTelegramBot(@Value("${telegram.bot.token}") String token,
//                                  @Value("${telegram.bot.username}") String username) {
//        super(token);
//        this.username = username;
//    }
//
//    @Scheduled(fixedRate = 10000)
//    public void sendMessageToMe() {
//        SendMessage sendMessage = new SendMessage();
//        String chatId = "786244782";
//        String text = "Hi";
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(text);
//        System.out.println(sendMessage);
//        try {
//            System.out.println(getMe());
//
//            if (!getMe().getIsBot()) {
//                execute(sendMessage);
//            }
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            SendMessage sendMessage = new SendMessage();
//
//            Long chatId = message.getChatId();
//            sendMessage.setChatId(String.valueOf(chatId));
//            sendMessage.setText("Hi," + message.getChat().getUserName());
//
//            System.out.println("message.getChat() " + message.getChat());
//            ChatMemberUpdated myChatMemberUpdated = update.getMyChatMember();
//            ChatMemberUpdated chatMemberUpdated = update.getChatMember();
//            System.out.println("myChatMemberUpdated " + myChatMemberUpdated);
//            System.out.println("chatMemberUpdated " + chatMemberUpdated);
//
//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return username;
//    }
//}
