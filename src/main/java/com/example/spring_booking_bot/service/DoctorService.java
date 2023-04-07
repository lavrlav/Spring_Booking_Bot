package com.example.spring_booking_bot.service;

import com.example.spring_booking_bot.entity.Doctor;
import com.example.spring_booking_bot.entity.enums.Specialisation;
import com.example.spring_booking_bot.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor findBySpecialisation(final Specialisation specialisation) {
        return doctorRepository.findBySpecialisation(specialisation);
    }

    public SendMessage getDoctorsList(final long chatId) {
        final String doctorListMsg = String.format(
                "%s%n%s",
                "Please choose a doctor you want to make an appointment with:",
                getFormattedDoctorsList());

        return SendMessage.builder()
                .text(doctorListMsg)
                .chatId(chatId)
                .build();
    }

    private String getFormattedDoctorsList() {
        return Arrays.stream(Specialisation.values()).map(s -> s.value).collect(Collectors.joining("\n"));
    }

}

