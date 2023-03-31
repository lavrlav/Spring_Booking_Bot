package com.example.spring_booking_bot.entity;

import com.example.spring_booking_bot.serviceUser.DoctorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="telegram_user")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="user_name")
    String username;

    @Column(name="telegram_id")
    String tgId;

    @Column(name="name")
    String name;

    @Column(name="wanted_doc")
    @Enumerated
    DoctorEnum doctorEnum;
}
