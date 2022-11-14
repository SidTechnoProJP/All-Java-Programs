package com.railway.registration.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//class containing train availability details

public class Availability {
    private int trainNumber;
    private String trainDays;
}
