package com.bookmyshow;

import java.util.Date;

public class Ticket {
    private static int idCounter = 0;
    private int ticketId;
    private String ticketOwner;
    private Date bookingTime;
    private int numberOfSeats;
    private Show bookedShow;

    public Ticket() {
        idCounter += 1;
        this.ticketId = idCounter;
    }

    public void setOwner(String owner) {
        this.ticketOwner = owner;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "owner='" + ticketOwner +
                ", bookingTime=" + bookingTime +
                ", numberOfSeats=" + numberOfSeats +
                ", bookedShow=" + bookedShow +
                '}';
    }

    public void setBookedShow(Show bookedShow) {
        this.bookedShow = bookedShow;
    }
}

