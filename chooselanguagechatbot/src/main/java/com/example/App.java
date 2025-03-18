package com.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyNewsBot());
            System.out.println("ربات با موفقیت شروع شد!");
        } catch (TelegramApiException e) {
            System.err.println("خطا موقع ثبت ربات: " + e.getMessage());
            // اینجا برنامه ادامه می‌ده
        }
        // این خط تضمین می‌کنه که برنامه متوقف نشه
        System.out.println("ربات همچنان در حال اجراست...");
    }
}