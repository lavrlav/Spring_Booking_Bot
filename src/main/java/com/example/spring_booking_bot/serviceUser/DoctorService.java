package com.example.spring_booking_bot.serviceUser;

import com.example.spring_booking_bot.entity.BookModel;
import com.example.spring_booking_bot.repos.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorService {
    private final BookRepo bookRepo;
    public void saveBook(BookModel b){
        bookRepo.save(b);
    }
}
