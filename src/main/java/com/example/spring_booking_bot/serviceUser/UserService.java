package com.example.spring_booking_bot.serviceUser;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final User user;
    SendMessage sendMessage = new SendMessage();
    Update update = new Update();
    public SendMessage saveUserAnonymous(User u) {

        user.setUsername(update.getMessage().getFrom().getUserName());
        user.setTgId(update.getMessage().getFrom().getId().toString());

        if (update.getMessage().getText().equals("Остаться анонимом")) {
            sendMessage.setText("Пользователь сохранен");
        }
        userRepo.save(u);
        return sendMessage;

    }

    public SendMessage saveUserName(User u){

        user.setUsername(update.getMessage().getFrom().getUserName());
        user.setTgId(update.getMessage().getFrom().getId().toString());

        if (update.getMessage().getText().equals("Оставить свое имя")) {
            sendMessage.setText("Пользователь сохранен");
            user.setName(update.getMessage().getFrom().getFirstName());
        }
        userRepo.save(u);
        return sendMessage;
    }
}
