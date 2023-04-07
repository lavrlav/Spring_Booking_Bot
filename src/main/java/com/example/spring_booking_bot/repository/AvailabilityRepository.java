package com.example.spring_booking_bot.repository;

import com.example.spring_booking_bot.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AvailabilityRepository  extends JpaRepository<Availability, Long> {
    List<Availability> findAllByDoctorId(long doctorId);
}
