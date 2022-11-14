package com.railway.registration.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//class containing passenger details

public class PassengerDetails {
    private int passengerId;
    private int passengerAge;
    private String passengerName;
    private String passengerGender;
}
