package com.example.spring_booking_bot.repos;

import com.example.spring_booking_bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    User findUserModelByTgId(String id);

}
