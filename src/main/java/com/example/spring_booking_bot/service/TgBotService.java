package com.example.spring_booking_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class TgBotService {

    public SendMessage getHelpMessage(final long chatId) {
        return SendMessage.builder()
                .text("""
                        Can't find right way to use this bot?
                        Use /make_appointment then choose a doctor and availability!
                        To see all your appointments use /my_appointments
                        """)
                .chatId(chatId)
                .build();
    }

    public SendMessage getBadRequestMessage(final long chatId) {
        return SendMessage.builder()
                .text("I dont have this function.\nUse /help to see all available commands")
                .chatId(chatId)
                .build();
    }

}
