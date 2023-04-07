package com.example.spring_booking_bot.entity.enums;

public enum Specialisation {
    CARDIOLOGIST("/cardiologist"),
    DERMATOLOGIST("/dermatologist"),
    DENTIST("/dentist"),
    GYNECOLOGIST("/gynecologist");

    public final String value;

    Specialisation(String value) {
        this.value = value;
    }
}
