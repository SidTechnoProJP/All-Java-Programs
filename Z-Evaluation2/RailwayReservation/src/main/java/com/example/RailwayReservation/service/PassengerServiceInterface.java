package com.example.RailwayReservation.service;

import com.example.RailwayReservation.exception.CustomException;
import com.example.RailwayReservation.model.Passenger;
import com.example.RailwayReservation.model.SeatType;
import com.example.RailwayReservation.model.TicketBooking;
import com.example.RailwayReservation.model.Train;

import java.sql.Date;
import java.util.List;

public interface PassengerServiceInterface {

    //search method to find the trains with source and destination
    List<Train> findAllTrain(String source, String destination) throws CustomException;

    //search method to search the trains with source and destination with date
    List<Train> findAllTrain(String source, String destination, Date travelDate) throws CustomException;


    //method to book the ticket with all passenger details
     String ticketBooking(int numberOfPassengers, SeatType seatType, long trainNumber, Date dateOfTravelling, List<Passenger> passenger);
}
