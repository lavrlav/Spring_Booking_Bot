package com.example.spring_booking_bot.models;

import javax.persistence.*;

@Table(name="telegram_user")
@Entity
public class UserModel {
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
