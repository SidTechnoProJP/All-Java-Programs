package com.railway.registration.controller;

import com.railway.registration.model.Availability;
import com.railway.registration.model.PassengerDetails;
import com.railway.registration.model.Seats;
import com.railway.registration.model.TrainDetails;
import com.railway.registration.service.PassengerServiceImpl;
import com.railway.registration.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TrainController {
    @Autowired
    TrainService trainService;

    @PostMapping("/addtrains")
   int addTrains(@RequestBody TrainDetails trainDetails){
        return  trainService.addTrains(trainDetails);
    }

    @PostMapping("/adddates")
    int addDates(@RequestBody Availability availability){
        return trainService.addAvailableDates(availability);
    }

    @PostMapping("/addseats")
    int addSeats(@RequestBody Seats seats){
        return trainService.addSeats(seats);
    }

    @DeleteMapping("/deletetrain/{id}")
    ResponseEntity<String> deleteTrain(@PathVariable int id){
        try{
            return ResponseEntity.of(Optional.of(trainService.deleteTrain(id)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/canceltrainticket/{ticketId}")
    ResponseEntity<String> cancelBooking(@PathVariable int ticketId){
        try {
            return ResponseEntity.of(Optional.of(trainService.cancelBooking(ticketId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getpassenger")
    List<PassengerDetails> getPassenger(){
        return trainService.getAllPassenger();
    }

    @GetMapping("/getpassengerbytrain/{trainId}")
    List<PassengerDetails> getPassengerTrain(@PathVariable int trainId) {
        return trainService.getPassengerByTrain(trainId);
    }
}
