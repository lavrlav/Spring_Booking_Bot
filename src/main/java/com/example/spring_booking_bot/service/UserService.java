package com.example.spring_booking_bot.service;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(final long chatId) {
        return userRepository.getReferenceById(chatId);
    }

    public SendMessage saveNewUser(final String firstName, final String lastName, final long chatId) {
        final User newUser = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .chatId(chatId)
                .build();
        userRepository.save(newUser);

        return SendMessage.builder()
                .text("""
                        Hello my friend! I will help you to make an appointment with a doctor!
                        Use /make_appointment to start! You can also use /help to see all available commands
                        """)
                .chatId(chatId)
                .build();
    }

}

