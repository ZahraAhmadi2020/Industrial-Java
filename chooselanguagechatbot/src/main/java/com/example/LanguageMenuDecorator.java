package com.example;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class LanguageMenuDecorator extends BotDecorator {
    public LanguageMenuDecorator(BotHandler decoratedBot) {
        super(decoratedBot);
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage message = decoratedBot.handle(update);

        // Only process if the update has a message with text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (text.equals("/start")) {
                message.setText("Please select your language:\nلطفاً زبان خود را انتخاب کنید:\nПожалуйста, выберите язык:");
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rows = new ArrayList<>();

                // Persian button
                List<InlineKeyboardButton> row1 = new ArrayList<>();
                InlineKeyboardButton persianButton = new InlineKeyboardButton();
                persianButton.setText("فارسی");
                persianButton.setCallbackData("lang_fa");
                row1.add(persianButton);

                // English button
                List<InlineKeyboardButton> row2 = new ArrayList<>();
                InlineKeyboardButton englishButton = new InlineKeyboardButton();
                englishButton.setText("English");
                englishButton.setCallbackData("lang_en");
                row2.add(englishButton);

                // Russian button
                List<InlineKeyboardButton> row3 = new ArrayList<>();
                InlineKeyboardButton russianButton = new InlineKeyboardButton();
                russianButton.setText("Русский");
                russianButton.setCallbackData("lang_ru");
                row3.add(russianButton);

                rows.add(row1);
                rows.add(row2);
                rows.add(row3);
                markup.setKeyboard(rows);
                message.setReplyMarkup(markup);
            }
        }
        // If no message or no text, just pass it to the next handler
        return message;
    }
}