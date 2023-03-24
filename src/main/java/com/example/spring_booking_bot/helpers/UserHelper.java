package com.example.spring_booking_bot.helpers;

import com.example.spring_booking_bot.models.UserModel;
import com.example.spring_booking_bot.repos.UserRepo;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    final
    UserRepo userRepo;

    public UserHelper(UserRepo userRepo) {
        this.userRepo = userRepo;
        helper = this;
    }

    private static UserHelper helper = null;

    public static void saveUser(UserModel u){
        helper.userRepo.save(u);
    }
}
