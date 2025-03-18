package com.example;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ChatDecorator extends BotDecorator {
    private static final Map<Long, String> userLanguages = new HashMap<>();

    public ChatDecorator(BotHandler decoratedBot) {
        super(decoratedBot);
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage message = decoratedBot.handle(update);

        // Get chat ID based on update type
        Long chatId = null;
        if (update.hasMessage()) { // No need for hasChat(), hasMessage() is enough
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }

        if (chatId == null) {
            message.setText("Error: Unable to find the chat!");
            return message;
        }

        // Handle language selection
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            switch (callbackData) {
                case "lang_fa":
                    userLanguages.put(chatId, "fa");
                    message.setText("Your language has been set to Persian. How can I help you?");
                    break;
                case "lang_en":
                    userLanguages.put(chatId, "en");
                    message.setText("Your language has been set to English. How can I assist you?");
                    break;
                case "lang_ru":
                    userLanguages.put(chatId, "ru");
                    message.setText("Your language has been set to Russian. How can I help you?");
                    break;
            }
            return message;
        }

        // Smart chat based on language
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userText = update.getMessage().getText();
            String language = userLanguages.getOrDefault(chatId, "en");

            if (!userText.startsWith("/")) {
                switch (language) {
                    case "fa":
                        message.setText("You said: " + userText + "\nMy response: Very good!");
                        break;
                    case "en":
                        message.setText("You said: " + userText + "\nMy response: Great!");
                        break;
                    case "ru":
                        message.setText("You said: " + userText + "\nMy response: Excellent!");
                        break;
                }
            }
        }
        return message;
    }
}