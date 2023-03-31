package com.example.spring_booking_bot.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name="appointment")
@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="tg_chat_id")
    String tgChatId;

    @Column(name="doctor")
    String doctor;

    @Column(name="time")
    String time;
}
