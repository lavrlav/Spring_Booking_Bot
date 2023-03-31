package com.example.spring_booking_bot.repos;

import com.example.spring_booking_bot.entity.BookModel;
import com.example.spring_booking_bot.serviceUser.DoctorEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookModel, Long> {
    List<BookModel> findBookModelByDoctorEnum(DoctorEnum d);

}
