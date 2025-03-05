package com.example;

 
// Создайте простого эхо-бота:
// После получения команды /start (update.message.text) бот должен начать слушать пользователя
// Любой текст отправленный боту сразу пишется ботом в ответ пользователю
 
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class App {
   /**
     * @param args
     */
    public static void main(String[] args) {
        String botToken = "7517482934:AAFmCTpZ-WV3gSBLW0m0eUDAuVNIeR7ad18";
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
          // statr bot
           
             botsApplication.registerBot(botToken, new LoggingTestBot(botToken));
            System.out.println("LoggingTestBot successfully started!");

          

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
