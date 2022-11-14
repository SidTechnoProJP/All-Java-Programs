package com.example.RailwayReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seats {

    private long train_number;
    private int availableAcSeats;
    private int availableGnSeats;
    private int bookedAcSeats;
    private int bookedGnSeats;
    private Date travelDate;
}
