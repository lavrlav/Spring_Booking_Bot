package com.example.spring_booking_bot.repository;

import com.example.spring_booking_bot.entity.Appointment;
import com.example.spring_booking_bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByUser(User user);
}
