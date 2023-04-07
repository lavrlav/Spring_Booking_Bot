package com.example.spring_booking_bot.repository;

import com.example.spring_booking_bot.entity.Doctor;
import com.example.spring_booking_bot.entity.enums.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findBySpecialisation(Specialisation specialisation);
}
