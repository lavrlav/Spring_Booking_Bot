package com.example.spring_booking_bot.serviceUser;

import com.example.spring_booking_bot.entity.BookModel;
import com.example.spring_booking_bot.repos.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorService {
    private final BookRepo bookRepo;
    private static DoctorService doctorService = null;
    public void saveBook(BookModel b){
        bookRepo.save(b);
    }

    public static List<String> getFreeTimes(DoctorEnum d){
        TimeControl timeControl = new TimeControl();
        List<BookModel> bookModelList = doctorService.bookRepo.findBookModelByDoctorEnum(d);
        List<String> freeTimes = new ArrayList<>();
        freeTimes = timeControl.getTimes();

        List<String> list = new ArrayList<>();
        for(BookModel b: bookModelList){
            for(String str: freeTimes){
                if(b.getTime().equals(str)){
                    list.add(b.getTime());

                }
            }
        }
        freeTimes.remove(list);
        return freeTimes;

    }
}
