package com.example.RailwayReservation.controller;

import com.example.RailwayReservation.model.Passenger;
import com.example.RailwayReservation.model.SeatType;
import com.example.RailwayReservation.model.Train;
import com.example.RailwayReservation.service.PassengerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PassengerController {

    @Autowired
    PassengerServiceInterface passengerServiceInterface;

    @GetMapping("/findtrain/{source}/{destination}")
    ResponseEntity<List<Train>> getTrain(@PathVariable String source, @PathVariable String destination) {

        try {
            return ResponseEntity.of(Optional.of(passengerServiceInterface.findAllTrain(source, destination)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findtrain/{source}/{destination}/{date}")
    ResponseEntity<List<Train>> getTrains(@PathVariable String source, @PathVariable String destination, @PathVariable Date date) {

        try {
            return ResponseEntity.of(Optional.of(passengerServiceInterface.findAllTrain(source, destination, date)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/{numberOfPassengers}/{trainNumber}/{dateOfTravelling}/{seatType}")
    ResponseEntity<String> bookTicket(@PathVariable int numberOfPassengers, @PathVariable long trainNumber,
                                      @PathVariable Date dateOfTravelling, @PathVariable SeatType seatType, @RequestBody List<Passenger> passengerList) {
        try {
            return ResponseEntity.of(Optional.of(passengerServiceInterface.ticketBooking(numberOfPassengers, seatType, trainNumber, dateOfTravelling, passengerList)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
