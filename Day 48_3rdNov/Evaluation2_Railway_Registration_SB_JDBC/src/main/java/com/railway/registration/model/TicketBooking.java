package com.railway.registration.model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//class containing ticket booking details

public class TicketBooking {
    private List<PassengerDetails> passengerDetailsList;
    int ticketId;
    int numberOfSeatsBooked;
    int trainNumber;
    Date bookingDate;
    Date dateOfTravel;
    private BookingStatus bookingStatus;
    private SeatType seatType;
    private PaymentStatus paymentStatus;
    String trainSource;
    String trainDestination;
}
