package com.bookmyshow;

public class TicketBooking extends Thread {
    private Show show;
    private RegisteredUser user;
    private int numberOfSeats;
    private Ticket ticket;

    public TicketBooking(Show show, RegisteredUser user, int numberOfSeats, PaymentStatus paymentStatus, PaymentMethod paymentMethod ) {
        this.show = show;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void run() {
        this.ticket = show.bookTicket(user, numberOfSeats);
    }

    public Ticket getTicket() {
        return ticket;
    }
}