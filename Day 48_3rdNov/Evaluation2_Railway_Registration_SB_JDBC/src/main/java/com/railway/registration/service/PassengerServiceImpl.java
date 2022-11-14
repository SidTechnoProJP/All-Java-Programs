package com.railway.registration.service;

import com.railway.registration.exception.CustomException;
import com.railway.registration.model.PassengerDetails;
import com.railway.registration.model.SeatType;
import com.railway.registration.model.TrainDetails;

import java.sql.Date;
import java.util.List;

public interface PassengerServiceImpl {

    //method to search the trains with train source location and train destination location
    List<TrainDetails> findAllTrain(String source, String destination) throws CustomException;

    //method to book tickets with passenger details
    String ticketBooking(int totalSeatsBooked, int trainNumber, Date dateOfTravel, SeatType seatType, List<PassengerDetails> passenger);

    //method to cancel booking
    String cancelTicketBooking(int ticketId);
}
