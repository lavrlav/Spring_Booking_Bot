package com.example.spring_booking_bot.service;

import com.example.spring_booking_bot.entity.Appointment;
import com.example.spring_booking_bot.entity.Availability;
import com.example.spring_booking_bot.entity.Doctor;
import com.example.spring_booking_bot.entity.User;
import com.example.spring_booking_bot.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final AvailabilityService availabilityService;
    private final UserService userService;

    @Transactional
    public SendMessage makeAppointment(final String receivedMessage, final long chatId) {
        final long availabilityId = toLongForAppointment(receivedMessage);
        final User user = userService.getById(chatId);
        Availability availability = availabilityService.getById(availabilityId);
        final Doctor doctor = availability.getDoctor();

        appointmentRepository.save(Appointment.builder()
                .availability(availability)
                .user(user)
                .doctor(doctor)
                .build());

        availability.setFree(false);
        availabilityService.save(availability);

        return SendMessage.builder()
                .text("Your appointment is confirmed! You can see it in /my_appointments")
                .chatId(chatId)
                .build();
    }

    @Transactional
    public SendMessage cancelAppointment(final String receivedMessage, final long chatId) {
        final long appointmentId = toLongForCancel(receivedMessage);
        final Appointment appointment = appointmentRepository.getReferenceById(appointmentId);

        Availability availability = availabilityService.getById(appointment.getAvailability().getId());
        availability.setFree(true);

        availabilityService.save(availability);
        appointmentRepository.delete(appointment);

        return SendMessage.builder()
                .text("Your appointment was cancelled!")
                .chatId(chatId)
                .build();
    }

    public SendMessage getAllAppointments(final long chatId) {
        final User user = userService.getById(chatId);
        final List<Appointment> appointmentList = appointmentRepository.findAllByUser(user);

        final String appointmentsResponse;
        if (appointmentList.isEmpty()) {
            appointmentsResponse = "You don't have any appointments";
        } else {
            appointmentsResponse = toAppointmentResponse(appointmentList);
        }

        return SendMessage.builder()
                .text(appointmentsResponse)
                .chatId(chatId)
                .build();
    }

    private long toLongForCancel(final String receivedMessage) {
        return Long.parseLong(receivedMessage.substring(8));
    }

    private long toLongForAppointment(final String receivedMessage) {
        return Long.parseLong(receivedMessage.substring(1));
    }

    private String toAppointmentResponse(final List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(appointment -> String.format("%s; %s %s%d",
                        appointment.getDoctor().getSpecialisation().toString().toLowerCase(),
                        appointment.getAvailability().getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "/cancel_", appointment.getId()))
                .collect(Collectors.joining("\n"));
    }

}

