package com.example.spring_booking_bot.bot;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.serviceBot.BookCommand;
import com.example.spring_booking_bot.serviceBot.LoginCommand;
import com.example.spring_booking_bot.serviceUser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final LoginCommand loginCommand;

    private final BookCommand bookCommand;
    private final UserService userService;
    // private final User user;

    @Override
    public String getBotUsername() {
        return "my_spring_medicine_bot";
    }

    @Override
    public String getBotToken() {
        return "6287934992:AAHjux_d1UY87hTBxIxdlyyxAUX-ypFNzD8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String requestMsg = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage();

        KeyboardRow k = new KeyboardRow();
        k.add(new KeyboardButton("Log In"));
        k.add(new KeyboardButton("Записаться к врачу"));

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите действие");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(k));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        String tgUserFName = update.getMessage().getFrom().getFirstName();
        String tgUserName = update.getMessage().getFrom().getUserName();
        String idChatTg = update.getMessage().getChatId().toString();


        switch (requestMsg) {
            case "Log In" -> sendMessage = loginCommand.start(update);
            case "Остаться анонимом" ->
                    sendMessage = userService.saveUserAnonymous(update.getMessage().getChatId().toString());
            case "Оставить свое имя" -> sendMessage = userService.saveUserName(tgUserFName, tgUserName, idChatTg);
            case "Записаться к врачу" -> sendMessage = bookCommand.start(update);
        }


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}

