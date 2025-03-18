package com.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class BotDecorator implements BotHandler {
    protected BotHandler decoratedBot;

    public BotDecorator(BotHandler decoratedBot) {
        this.decoratedBot = decoratedBot;
    }

    @Override
    public SendMessage handle(Update update) {
        return decoratedBot.handle(update);
    }
}