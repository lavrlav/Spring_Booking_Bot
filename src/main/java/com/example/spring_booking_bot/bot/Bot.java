package com.example.spring_booking_bot.bot;

import com.example.spring_booking_bot.entity.enums.Specialisation;
import com.example.spring_booking_bot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private final UserService userService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final AvailabilityService availabilityService;
    private final TgBotService botService;


    @Override
    public String getBotUsername() {
        return "my_spring_medicine_bot";
    }

    @Override
    public String getBotToken() {
        return "6287934992:AAHjux_d1UY87hTBxIxdlyyxAUX-ypFNzD8";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        final String receivedMessage = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();
        SendMessage responseMsg;

        if (receivedMessage.equals("/start")) {
            final String firstName = update.getMessage().getFrom().getFirstName();
            final String lastName = update.getMessage().getFrom().getLastName();
            responseMsg = userService.saveNewUser(firstName, lastName, chatId);
        } else if (receivedMessage.equals("/make_appointment")) {
            responseMsg = doctorService.getDoctorsList(chatId);
        } else if (checkSpecialisation(receivedMessage)) {
            responseMsg = availabilityService.getAvailabilities(receivedMessage, chatId);
        } else if (receivedMessage.equals("/my_appointments")) {
            responseMsg = appointmentService.getAllAppointments(chatId);
        } else if (receivedMessage.equals("/help")) {
            responseMsg = botService.getHelpMessage(chatId);
        } else if (receivedMessage.startsWith("/cancel_")) {
            responseMsg = appointmentService.cancelAppointment(receivedMessage, chatId);
        } else if (checkAvailabilities(receivedMessage.substring(1))) {
            responseMsg = appointmentService.makeAppointment(receivedMessage, chatId);
        } else {
            responseMsg = botService.getBadRequestMessage(chatId);
        }

        execute(responseMsg);
    }

    private boolean checkSpecialisation(final String receivedMessage) {
        final List<String> specialisations = Arrays.stream(Specialisation.values())
                .map(s -> s.value)
                .toList();
        return specialisations.contains(receivedMessage);
    }

    private boolean checkAvailabilities(final String idFromMessage) {
        final long availabilityId;
        try {
            availabilityId = Long.parseLong(idFromMessage);
        } catch (NumberFormatException e) {
            return false;
        }
        return availabilityService.findById(availabilityId).isPresent();
    }

}

