package io.github.guilhermemelo01.springbotjava.entities;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");

    @Override
    public String getBotUsername() {
        return "java-bot";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();
        System.out.println("message: "+ message);
//        System.out.println(user);

//        sendMenu(user, "fodase", keyboardM2);
    }

    public void sendPrivateMessage(User user, String message){
        SendMessage sm = SendMessage.builder().chatId(user.getId().toString()).text(message).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    InlineKeyboardButton buttonNext = InlineKeyboardButton.builder()
            .text("Next").callbackData("next")
            .build();

    InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
            .text("Back").callbackData("back")
            .build();

    InlineKeyboardButton buttonURL = InlineKeyboardButton.builder()
            .text("Tutorial")
            .url("https://core.telegram.org/bots/api")
            .build();

    private boolean screaming = false;
    private InlineKeyboardMarkup keyboardM2 = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(buttonNext))
            .keyboardRow(List.of(buttonBack))
            .keyboardRow(List.of(buttonURL))
            .build();

    public void sendMenu(User user, String txt, InlineKeyboardMarkup kb){
        SendMessage sm = SendMessage.builder().chatId(user.getId().toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}