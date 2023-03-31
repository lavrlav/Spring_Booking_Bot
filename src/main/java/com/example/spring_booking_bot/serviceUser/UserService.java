package com.example.spring_booking_bot.serviceUser;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private static UserService userService = null;

    public SendMessage saveUserAnonymous(String tgChatId) {
        User anonUser = User.builder()
                .tgId(tgChatId)
                .build();
        userRepo.save(anonUser);
        return SendMessage.builder()
                .text("Пользователь сохранен")
                .chatId(tgChatId)
                .build();
    }

    public SendMessage saveUserName(String nameTg, String userName, String tgChatId) {
        User nameUser = User.builder()
                .tgId(tgChatId)
                .name(nameTg)
                .username(userName)
                .build();
        userRepo.save(nameUser);
        return SendMessage.builder()
                .text("Пользователь сохранен")
                .chatId(tgChatId)
                .build();
    }

    public static User findUser(String tgId){
       User user;
        user = userService.userRepo.findUserModelByTgId(tgId);
        return user;
    }
}
