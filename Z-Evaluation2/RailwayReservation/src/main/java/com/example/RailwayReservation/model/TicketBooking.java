package com.example.RailwayReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketBooking {

    private long ticketId;
    private int numberOfPassenger;
    private long trainNumber;
    private Date bookingDate;
    private Date dateOfTravel;
    private BookingStatus bookingStatus;
    private SeatType seatType;
    private String source;
    private String destination;
    private List<Passenger> passengerList;

}
