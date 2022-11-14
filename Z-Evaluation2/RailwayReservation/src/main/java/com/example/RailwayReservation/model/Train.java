package com.example.RailwayReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    private long trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private float acSeatPrice;
    private float gnSeatPrice;
    private List<Date> travelDate = new ArrayList<>();
}
