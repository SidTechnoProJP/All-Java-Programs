package com.railway.registration.service;

import com.railway.registration.model.*;

import java.util.List;

public interface TrainServiceImpl {
    //method to add trains
    int addTrains(TrainDetails train);

    //method to add availability
    int addAvailableDates(Availability availability);

    //method to add seats in train
    int addSeats(Seats seats);

    //method to delete a train by id
    String deleteTrain(int trainNumber);

    String cancelBooking(int ticketId);

    List<PassengerDetails> getAllPassenger();

    List<PassengerDetails> getPassengerByTrain(int trainNumber);
}
