package com.example.spring_booking_bot.repos;

import com.example.spring_booking_bot.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookModel, Long> {

}
