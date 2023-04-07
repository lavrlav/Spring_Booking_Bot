package com.example.spring_booking_bot.service;

import com.example.spring_booking_bot.entity.Availability;
import com.example.spring_booking_bot.entity.Doctor;
import com.example.spring_booking_bot.entity.enums.Specialisation;
import com.example.spring_booking_bot.repository.AvailabilityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final DoctorService doctorService;

    public void save(final Availability availability) {
        availabilityRepository.save(availability);
    }

    public Optional<Availability> findById(final long id) {
        return availabilityRepository.findById(id);
    }

    public Availability getById(final long id) {
        return availabilityRepository.getReferenceById(id);
    }

    public SendMessage getAvailabilities(final String specialisationStr, final long chatId) {
        final Specialisation specialisation = toSpecialisation(specialisationStr);
        final Doctor doctor = doctorService.findBySpecialisation(specialisation);

        String availability;
        if (doctor == null) {
            availability = "Sorry, we don't have available appointments with such doctor";
        } else {
            availability = String.format("%s%n%s",
                    "Please choose right option to make an appointment:",
                    getFormattedAvailabilitiesByDoctor(doctor));
        }
        return SendMessage.builder()
                .text(availability)
                .chatId(chatId)
                .build();
    }

    private String getFormattedAvailabilitiesByDoctor(final Doctor doctor) {
        return availabilityRepository.findAllByDoctorId(doctor.getId()).stream()
                .filter(Availability::isFree)
                .sorted(Comparator.comparing(Availability::getDate))
                .map(a -> toAvailabilityResponse(a, doctor.getName(), doctor.getSpecialisation()))
                .collect(Collectors.joining("\n"));
    }

    private String toAvailabilityResponse(
            final Availability availability,
            final String doctorName,
            final Specialisation doctorSpecialisation
    ) {
        final long id = availability.getId();
        final String formattedDate = availability.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        final String formattedSpecialisation = doctorSpecialisation.toString().toLowerCase();

        return String.format("/%d %s; %s; %s", id, formattedDate, doctorName, formattedSpecialisation);
    }

    private Specialisation toSpecialisation(String receivedMessage) {
        return Specialisation.valueOf(receivedMessage.substring(1).toUpperCase());
    }

}

