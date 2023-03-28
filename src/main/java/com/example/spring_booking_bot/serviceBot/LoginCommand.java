package com.example.spring_booking_bot.serviceBot;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.serviceUser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class LoginCommand implements WorkerCommand {
    private final UserService userService;

    @Override
    public SendMessage start(Update update) {
        if (!update.getMessage().getText().equals("Log In")
                && !update.getMessage().getText().equals("Оставить свое имя") && !update.getMessage().getText().equals("Остаться анонимом")) {
            return null;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите действие");
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (update.getMessage().getText().equals("Log In")) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Оставить свое имя"));
            keyboardRow.add(new KeyboardButton("Остаться анонимом"));

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));

            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            return sendMessage;
        }

       return sendMessage;

    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}


