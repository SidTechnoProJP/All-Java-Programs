package com.example.RailwayReservation.service;

import com.example.RailwayReservation.exception.CustomException;
import com.example.RailwayReservation.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class PassengerService implements PassengerServiceInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;


    //search method to find the trains with source and destination
    @Override
    public List<Train> findAllTrain(String source, String destination) throws CustomException {

        //query to search the train
        try {
            List<Train> trainList = jdbcTemplate.query("select * from train where source = ? and destination = ?",
                    new BeanPropertyRowMapper<>(Train.class), source, destination);
            for (Train train : trainList) {
                List<Seats> seats = jdbcTemplate.query("select travel_date from seats where train_number=?",
                        new BeanPropertyRowMapper<>(Seats.class), train.getTrainNumber());
                for (Seats s : seats) {
                    train.getTravelDate().add(s.getTravelDate());

                }
            }
            return trainList;
        } catch (Exception e) {
            throw new CustomException("not found");
        }
    }

    //search method to search the trains with source and destination with date
    @Override
    public List<Train> findAllTrain(String source, String destination, Date travelDate) throws CustomException {

        //search query
        try {
            return jdbcTemplate.query("select * from train where source=? and destination=? and train_number in" +
                            "(select train_number from seats where travel_date=?)",
                    new BeanPropertyRowMapper<>(Train.class), source, destination, travelDate);
        } catch (Exception e) {
            throw new CustomException("not found");
        }

    }

    //method to book a ticket with passenger details
    @Override
    public String ticketBooking(int numberOfPassengers, SeatType seatType, long trainNumber, Date dateOfTravelling, List<Passenger> passenger) {
        try {
            List<Train> trainList = jdbcTemplate.query("select * from train where train_number=?", new BeanPropertyRowMapper<>(Train.class), trainNumber);
            jdbcTemplate.update("insert into ticket_booking(number_of_passenger,train_number,booking_date,date_of_travel,booking_status,seat_type,source,destination) " +
                            "values(?,?,?,?,?,?,?,?)", numberOfPassengers, trainNumber, LocalDate.now(),
                    dateOfTravelling, BookingStatus.CONFIRMED.toString(), seatType.toString(), trainList.get(0).getSource(), trainList.get(0).getDestination());
            long ticketId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
            //Adding passenger
            List<Long> passId = new ArrayList<>();
            for (Passenger p : passenger) {
                jdbcTemplate.update("insert into passenger(passenger_name, gender,age,phone_number) value (?,?,?,?) ",
                        p.getPassengerName(), p.getGender(), p.getAge(), p.getPhoneNumber());
                passId.add(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
            }
            for (Long p : passId) {
                jdbcTemplate.update("insert into ticket_passenger values(?,?)", ticketId, p);
            }
            return "Ticket Booked Successfully";
        } catch (Exception e) {
            throw e;
        }
    }


}
