package com.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SimpleBot implements BotHandler {
    @Override
    public SendMessage handle(Update update) {
        SendMessage message = new SendMessage();
        
        // چک کن اگه آپدیت پیام متنی داره
        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setChatId(update.getMessage().getChatId().toString());
            String text = update.getMessage().getText();
            if (text.equals("/start")) {
                message.setText("خوش اومدی! لطفاً زبانت رو انتخاب کن.");
            } else if (text.equals("/myname")) {
                message.setText("اسم من MyNewsBot هست! تو اسمت چیه؟");
            } else {
                message.setText("دستور رو نمی‌شناسم! فقط /start و /myname رو بلدم.");
            }
        } else {
            message.setText("در حال پردازش...");
        }
        return message;
    }
}