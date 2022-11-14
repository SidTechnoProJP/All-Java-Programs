package com.railway.registration.service;

import com.railway.registration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService implements TrainServiceImpl {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //method to add train details
    @Override
    public int addTrains(TrainDetails train) {
        return jdbcTemplate.update("INSERT INTO train_info VALUES (?,?,?,?,?,?)", train.getTrainNumber(), train.getTrainName(), train.getTrainSource(), train.getTrainDestination(), train.getAcSeatPrice(), train.getGenSeatPrice());
    }

    //method to add availability
    @Override
    public int addAvailableDates(Availability availability) {
        return jdbcTemplate.update("INSERT INTO available_dates VALUES (?,?)", availability.getTrainNumber(), availability.getTrainDays());
    }


    //method to add seats
    @Override
    public int addSeats(Seats seats) {
        return jdbcTemplate.update("INSERT INTO seats VALUES (?,?,?,?,?,?)", seats.getTrainNumber(), seats.getTravelDate(), seats.getAvailableAcSeats(), seats.getAvailableGenSeats(), seats.getBookedAcSeats(), seats.getBookedGenSeats());
    }


    //method to delete train
    @Override
    public  String deleteTrain(int trainNumber) {
        try {
            jdbcTemplate.update("delete from train_info where train_number = ?",trainNumber);
            return "Train deleted with id "+ trainNumber;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String cancelBooking(int ticketId) {
        try {
            TicketBooking ticketBooking = jdbcTemplate.queryForObject("select * from ticket_booking_info where ticket_id=?",
                    new BeanPropertyRowMapper<>(TicketBooking.class), ticketId);
            if (ticketBooking != null) {
                jdbcTemplate.update("delete from passenger_ticket where ticket_id=?", ticketId);
                jdbcTemplate.update("delete from ticket_booking_info where ticket_id=?", ticketId);
            }
            return "Ticket Cancelled";
        } catch (Exception e) {
            return "Invalid!";
        }
    }

    //method to get list of all passengers
    @Override
    public List<PassengerDetails> getAllPassenger() {
        try{
            return jdbcTemplate.query("select * from passenger_info",new BeanPropertyRowMapper<>(PassengerDetails.class));
        }catch(Exception e){
            throw e;
        }
    }

    //method to get list of all passengers by train number
    @Override
    public List<PassengerDetails> getPassengerByTrain(int trainNumber) {
        try {
            return jdbcTemplate.query("select * from passenger_info where passenger_id in(select passenger_id from passenger_ticket where ticket_id in (select ticket_id from ticket_booking_info where train_number=?))",new BeanPropertyRowMapper<>(PassengerDetails.class),trainNumber);
        }catch (Exception e){
            throw e;
        }
    }
}
