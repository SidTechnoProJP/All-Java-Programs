package com.railway.registration.model;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//class containing seat details

public class Seats {
    private int trainNumber;

    private int availableAcSeats;
    private int bookedAcSeats;

    private int availableGenSeats;
    private int bookedGenSeats;

    private int availableAcBooked;
    private int availableAcWaiting;

    private int availableGenBooked;
    private int availableGenWaiting;

    private int bookedAcWaiting;
    private int bookedGenWaiting;

    private Date travelDate;

}
