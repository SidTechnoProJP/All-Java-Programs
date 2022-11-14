package com.railway.registration.model;

import lombok.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//class containing train details

public class TrainDetails {
    private List<Date> trainTravelDate = new ArrayList<>();
    private int trainNumber;
    private String trainName;
    private String trainSource;
    private String trainDestination;
    private int acSeatPrice;
    private int genSeatPrice;
}
