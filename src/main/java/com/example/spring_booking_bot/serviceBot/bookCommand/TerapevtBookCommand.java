package com.example.spring_booking_bot.serviceBot.bookCommand;

import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.serviceBot.WorkerCommand;
import com.example.spring_booking_bot.serviceUser.DoctorEnum;
import com.example.spring_booking_bot.serviceUser.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TerapevtBookCommand implements WorkerCommand {
    private final UserService userService = null;
    @Override
    public SendMessage start(Update update) {
        if(!update.getMessage().getText().equals("Тервпевт")){
            return null;
        }
        User user = UserService.findUser(update.getMessage().getFrom().getId().toString());
        user.setDoctorEnum(DoctorEnum.TERAPEVT);
        userService.saveUserName(user);
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
