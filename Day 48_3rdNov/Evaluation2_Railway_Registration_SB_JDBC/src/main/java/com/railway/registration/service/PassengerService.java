package com.railway.registration.service;

import com.railway.registration.exception.CustomException;
import com.railway.registration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PassengerService implements PassengerServiceImpl {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Searching trains with source and destination
    @Override
    public List<TrainDetails> findAllTrain(String source, String destination) throws CustomException {
        try {
            List<TrainDetails> trainList = jdbcTemplate.query("select * from train_info where train_source = ? and train_destination = ?",
                    new BeanPropertyRowMapper<>(TrainDetails.class), source, destination);
            for (TrainDetails train : trainList) {
                List<Seats> seats = jdbcTemplate.query("select travel_date from seats where train_number=?",
                        new BeanPropertyRowMapper<>(Seats.class), train.getTrainNumber());
                for (Seats s : seats) {
                    train.getTrainTravelDate().add(s.getTravelDate());
                }
            }
            return trainList;
        } catch (Exception e) {
            throw new CustomException("not found");
        }
    }

    //method to check booking
    int checkBooking(int totalSeatsBooked, int trainNumber, Date dateOfTravel, SeatType seatType) {
        int flag = 0;
        if(!(dateOfTravel.toLocalDate().isAfter(LocalDate.now()) && dateOfTravel.toLocalDate().isBefore(LocalDate.now().plusDays(7)))) {
            return flag = 5;
        }
        List<Seats> seatsList = jdbcTemplate.query("select * from seats where train_number=? and travel_date=?", new BeanPropertyRowMapper<>(Seats.class), trainNumber, dateOfTravel);
        if (!seatsList.isEmpty()) {
            for (Seats s : seatsList) {
                if (Objects.equals(seatType.toString(), "AC")) {

                    if (s.getAvailableAcSeats() >= totalSeatsBooked) {
                        flag = 3;
                        break;
                    } else {
                        if (s.getAvailableAcWaiting() >= totalSeatsBooked && (totalSeatsBooked + s.getBookedAcSeats() <= 12) && s.getBookedAcWaiting() < 2) {
                            flag = 1;
                        } else {
                            flag = 4;
                        }
                        break;
                    }

                } else if (Objects.equals(seatType.toString(), "GENERAL")) {
                    System.out.println(s.getAvailableGenWaiting());
                    if (s.getAvailableGenSeats() >= totalSeatsBooked) {
                        flag = 3;
                        break;
                    } else {
                        if (s.getAvailableGenWaiting() >= totalSeatsBooked && (totalSeatsBooked + s.getBookedGenSeats() <= 12) && s.getBookedGenWaiting() < 2) {
                            flag = 1;
                        } else {
                            flag = 4;
                        }
                        break;
                    }
                } else {
                    flag = 4;
                }
            }
        }
        return flag;
    }

    @Override
    public String ticketBooking(int totalSeatsBooked, int trainNumber, Date dateOfTravel, SeatType seatType, List<PassengerDetails> passenger) {
        int val = checkBooking(totalSeatsBooked, trainNumber, dateOfTravel, seatType);
        if (val == 5) {
            return "Booking is not opened.";
        }
        if (val == 3) {
            try {
                List<TrainDetails> trainList = jdbcTemplate.query("select * from train_info where train_number=?", new BeanPropertyRowMapper<>(TrainDetails.class), trainNumber);
                jdbcTemplate.update("insert into ticket_booking_info(number_of_seats_booked,train_number,booking_date,date_of_travel,seat_type,booking_status,payment_status,train_source,train_destination) " +
                                "values(?,?,?,?,?,?,?,?,?)", totalSeatsBooked, trainNumber, LocalDate.now(),
                        dateOfTravel, seatType.toString(), BookingStatus.CONFIRMED.toString(), PaymentStatus.COMPLETE.toString(), trainList.get(0).getTrainSource(), trainList.get(0).getTrainDestination());
                long ticketId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
                //Adding passenger
                List<Long> passId = new ArrayList<>();
                for (PassengerDetails p : passenger) {
                    jdbcTemplate.update("insert into passenger_info(passenger_id, passenger_name, passenger_gender,passenger_age) value (?,?,?,?) ",
                            p.getPassengerId(), p.getPassengerName(), p.getPassengerGender(), p.getPassengerAge());
                    passId.add(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
                }
                for (Long p : passId) {
                    jdbcTemplate.update("insert into passenger_ticket values(?,?)", ticketId, p);
                }

                Seats seats = jdbcTemplate.queryForObject("select * from seats where train_number=? and travel_date=?", new BeanPropertyRowMapper<>(Seats.class), trainNumber, dateOfTravel);
                //To update AC seats
                if (Objects.equals(seatType.toString(), "AC")) {
                    jdbcTemplate.update("update seats set available_ac_seats=?,booked_ac_seats=? where train_number=? and travel_date=? ",
                            seats.getAvailableAcSeats() - totalSeatsBooked, seats.getBookedAcSeats() + totalSeatsBooked, trainNumber, dateOfTravel);
                }

                //To update General seats
                if (Objects.equals(seatType.toString(), "GENERAL")) {
                    System.out.println(seats.getAvailableGenSeats() + " " + totalSeatsBooked);
                    jdbcTemplate.update("update seats set available_gen_seats=?,booked_gen_seats=? where train_number=? and travel_date=? ",
                            seats.getAvailableGenSeats() - totalSeatsBooked, seats.getBookedGenSeats() + totalSeatsBooked, trainNumber, dateOfTravel);
                }

                return "Ticket Booked Successfully!";
            } catch (Exception e) {
                throw e;
            }
        } else if (val == 1) {  //AC waiting seats and General waiting seats are updated;
            try {
                List<TrainDetails> trainList = jdbcTemplate.query("select * from train_info where train_number=?", new BeanPropertyRowMapper<>(TrainDetails.class), trainNumber);
                jdbcTemplate.update("insert into ticket_booking_info(number_of_passenger,train_number,booking_date,date_of_travel,seat_type,booking_status,payment_status,train_source,train_destination) " +
                                "values(?,?,?,?,?,?,?,?,?)", totalSeatsBooked, trainNumber, LocalDate.now(),
                        dateOfTravel, seatType.toString(), BookingStatus.WAITING.toString(), PaymentStatus.COMPLETE.toString(), trainList.get(0).getTrainSource(), trainList.get(0).getTrainDestination());
                long ticketId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);


                //Adding passenger
                List<Long> passengerId = new ArrayList<>();
                for (PassengerDetails p : passenger) {
                    jdbcTemplate.update("insert into passenger_info(passenger_id, passenger_name, passenger_gender,passenger_age) value (?,?,?,?) ",
                            p.getPassengerName(), p.getPassengerName(), p.getPassengerGender(), p.getPassengerAge());
                    passengerId.add(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
                }
                for (Long p : passengerId) {
                    jdbcTemplate.update("insert into passenger_ticket values(?,?)", ticketId, p);
                }


                Seats seats = jdbcTemplate.queryForObject("select * from seats where train_number=? and travel_date=?",
                        new BeanPropertyRowMapper<>(Seats.class), trainNumber, dateOfTravel);
                //To update AC waiting seats
                if (Objects.equals(seatType.toString(), "AC")) {
                    jdbcTemplate.update("update seats set available_ac_waiting=?,booked_ac_waiting=? where train_number=? and travel_date=? ",
                            seats.getAvailableAcWaiting() - totalSeatsBooked, seats.getBookedAcWaiting() + totalSeatsBooked,
                            trainNumber, dateOfTravel);
                }

                //To update General waiting seats
                if (Objects.equals(seatType.toString(), "GENERAL")) {
                    jdbcTemplate.update("update seats set available_gn_waiting=?,booked_gn_waiting=?" +
                                    " where train_number=? and travel_date=? ", seats.getAvailableGenWaiting() - totalSeatsBooked,
                            seats.getBookedGenWaiting() + totalSeatsBooked, trainNumber, dateOfTravel);
                }
                return "Ticket is in waiting status";

            } catch (Exception e) {
                return e.getMessage();
            }
        } else if (val == 4) {
            return "Booking full";

        } else if (val == 2) {
            return "Booking not yet opened";

        } else {
            return "Invalid train id or train is not available for the given date";
        }
    }

    //method to cancel booked ticket
    @Override
    public String cancelTicketBooking(int ticketId) {
        try {
            TicketBooking ticketBooking = jdbcTemplate.queryForObject("select * from ticket_booking_info where ticket_id=?",
                    new BeanPropertyRowMapper<>(TicketBooking.class), ticketId);
            if (ticketBooking != null) {
                jdbcTemplate.update("delete from passenger_ticket where ticket_id=?", ticketId);
                jdbcTemplate.update("delete from ticket_booking_info where ticket_id=?", ticketId);
            }
            return "Ticket Cancelled";
        } catch (Exception e) {
            System.out.println("Invalid!");
            throw e;
        }
    }
}
