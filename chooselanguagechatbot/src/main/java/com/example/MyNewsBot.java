package com.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyNewsBot extends TelegramLongPollingBot {
    private final BotHandler botHandler;

    public MyNewsBot() {
        BotHandler simpleBot = new SimpleBot();
        BotHandler langBot = new LanguageMenuDecorator(simpleBot);
        this.botHandler = new ChatDecorator(langBot);
    }

    @Override
    public String getBotUsername() {
        return "z.cat_bot"; // Your bot's username
    }

    @Override
    public String getBotToken() {
        return "7517482934:AAFmCTpZ-WV3gSBLW0m0eUDAuVNIeR7ad18"; // Your bot's token
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = botHandler.handle(update);

        // Set chat ID based on update type
        if (update.hasMessage()) { // No need for hasChat(), hasMessage() is enough
            message.setChatId(update.getMessage().getChatId().toString());
        } else if (update.hasCallbackQuery()) {
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        } else {
            System.err.println("Unknown update: " + update);
            message.setChatId(update.hasMessage() ? update.getMessage().getChatId().toString() : "0");
            message.setText("Sorry, I can't process this update!");
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}