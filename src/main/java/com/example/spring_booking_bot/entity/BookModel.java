package com.example.spring_booking_bot.entity;

import com.example.spring_booking_bot.serviceUser.DoctorEnum;
import lombok.Data;

import javax.persistence.*;

@Table(name="book_list")
@Entity
@Data
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="doctor")
    @Enumerated
    DoctorEnum doctorEnum;

    @Column(name="time")
    String time;

    @Column(name="tg_id")
    String tgId;
}
