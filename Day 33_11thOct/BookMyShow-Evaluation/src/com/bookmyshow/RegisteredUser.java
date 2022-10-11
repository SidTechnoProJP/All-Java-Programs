package com.bookmyshow;
import java.util.ArrayList;
public class RegisteredUser extends User {
	public ArrayList<Ticket> bookingHistory;

    public RegisteredUser(String name) {
        super(name);
        this.bookingHistory = new ArrayList<>();
    }
    public void makeBooking(){

    }

    public void showBooking(){

    }

    public void cancelTicket(Ticket ticket){

    }

}
