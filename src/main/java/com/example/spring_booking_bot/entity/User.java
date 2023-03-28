package com.example.spring_booking_bot.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name="telegram_user")
@Entity
@Data
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
}
