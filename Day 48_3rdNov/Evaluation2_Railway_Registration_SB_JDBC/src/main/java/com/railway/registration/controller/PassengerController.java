package com.railway.registration.controller;

import com.railway.registration.model.PassengerDetails;
import com.railway.registration.model.SeatType;
import com.railway.registration.model.TrainDetails;
import com.railway.registration.exception.CustomException;
import com.railway.registration.service.PassengerService;
import com.railway.registration.service.PassengerServiceImpl;
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
    PassengerServiceImpl passengerServiceImpl;

    @GetMapping("/findtrain/{train_source}/{train_destination}")
    ResponseEntity<List<TrainDetails>> getTrain(@PathVariable String train_source, @PathVariable String train_destination) {
        try {
            return ResponseEntity.of(Optional.of(passengerServiceImpl.findAllTrain(train_source, train_destination)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/bookticket/{numberOfSeatsBooked}/{trainNumber}/{dateOfTravel}/{seatType}")
    ResponseEntity<String> bookTicket(@PathVariable int numberOfSeatsBooked, @PathVariable int trainNumber,
                                      @PathVariable Date dateOfTravel, @PathVariable SeatType seatType, @RequestBody List<PassengerDetails> passengerList) {
        try {
            return ResponseEntity.of(Optional.of(passengerServiceImpl.ticketBooking(numberOfSeatsBooked,  trainNumber, dateOfTravel, seatType,passengerList)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/cancelticket/{ticketId}")
    ResponseEntity<String> cancelTicketBooking(@PathVariable int ticketId){
    try {
        return ResponseEntity.of(Optional.of(passengerServiceImpl.cancelTicketBooking(ticketId)));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }
}
