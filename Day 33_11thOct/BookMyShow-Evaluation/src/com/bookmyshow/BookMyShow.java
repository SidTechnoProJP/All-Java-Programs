package com.bookmyshow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BookMyShow {
    static HashMap<String, ArrayList<Show>> movieMap;
    ArrayList<Theatre> theaters;

    public BookMyShow(ArrayList<Theatre> theaters) {
        this.theaters = theaters;
        movieMap = new HashMap<>();
        generateMovieMap();
        //System.out.println(movieMap);
    }

    private void generateMovieMap() {
        for (Theatre theater : theaters) {
            ArrayList<Show> showArray = theater.getShows();
            for (Show show : showArray) {
                if (show != null) {
                    if (movieMap.containsKey(show.getMovie().getMovieName())) {
                        movieMap.get(show.getMovie().getMovieName()).add(show);
                    } else {
                        ArrayList<Show> movieShowList = new ArrayList<>();
                        movieShowList.add(show);
                        movieMap.put(show.getMovie().getMovieName(), movieShowList);
                    }
                }
            }
        }
    }

    public static ArrayList<Show> searchShows(String movieName) {
        ArrayList<Show> showList = new ArrayList<>();
        for(String key: movieMap.keySet()){
            for(int i=0; i< movieMap.get(key).size(); i++) {
                if (movieMap.get(key).get(i).getMovie().getMovieName().equalsIgnoreCase("Avengers"))
                    showList.add(movieMap.get(key).get(i));
            }
        }
        return showList;
    }

    public static void main(String[] args) {

        //Creating Admin User
        Admin admin = new Admin(" Sid");

        //Creating Front Desk Officer
        FrontDeskOfficer frontDeskOfficer = new FrontDeskOfficer("Max");

        // Creating Guest User Kim
        GuestUser guestUser1 = new GuestUser("Kim");

        // Creating Registered User 1 Ben
        RegisteredUser registeredUser1 = new RegisteredUser("Ben");

        // Creating Registered User 2 Jim
        RegisteredUser registeredUser2 = new RegisteredUser("Jim");

        // Creating Movie 1 object Iron Man
        Movie ironMan = new Movie("Iron Man", Language.ENGLISH, Genre.ACTION);

        // Creating Movie 2 object Avengers
        Movie avengers = new Movie("Avengers", Language.ENGLISH, Genre.ACTION);

        // Creating Movie 3 object Anabelle
        Movie anabelle = new Movie("Anabelle", Language.ENGLISH, Genre.HORROR);

        // Creating Movie 4 object Golmaal
        Movie golmaal = new Movie("Golmaal", Language.HINDI, Genre.COMEDY);

        // Creating Theater 1 PVR at Baner with capacity 50
        Theatre pvr = new Theatre("PVR", "Baner", 50);

        // Creating Theater 2 INOX at Koregaon Park with capacity 40
        Theatre inox = new Theatre("Inox", "Koregaon Park", 50);


        // Creating four shows for movies
        Show show1 = null, show2 = null, show3 = null, show4 = null, show5 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

        try {
            // Creating Show for Movie Iron Man on 7 Oct 2020 @ 9:00 AM in PVR
            String dateInString = "Friday, Oct 7, 2022 09:00:00 AM";
            Date date = formatter.parse(dateInString);
            show1 = new Show(date, ironMan, pvr);

            // Creating Show for Movie Golmaal on 7 Oct 2022 @ 12:00 PM in PVR
            dateInString = "Friday, Oct 7, 2022 12:00:00 PM";
            date = formatter.parse(dateInString);
            show2 = new Show(date, golmaal, pvr);

            // Creating Show for Movie Anabelle on 7 Oct 2022 @ 09:00 AM in INOX
            dateInString = "Friday, Oct 7, 2022 09:00:00 AM";
            date = formatter.parse(dateInString);
            show3 = new Show(date, anabelle, inox);

            // Creating Show for Movie Avengers on 7 Oct 2022 @ 12:00 PM in INOX
            dateInString = "Friday, Oct 7, 2022 12:00:00 PM";
            date = formatter.parse(dateInString);
            show4 = new Show(date, avengers, inox);

            dateInString = "Friday, Oct 7, 2022 06:00:00 AM";
            date = formatter.parse(dateInString);
            show5 = new Show(date, avengers, inox);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("***************************************PUNE CITY**************************************************");
        System.out.println("Showing shows for PVR: " + "\n" + show1 + "\n" + show2);
        System.out.println("\nShowing shows for INOX: " + "\n" + show3 + "\n" + show4 + "\n" + show5);
        System.out.println("**************************************************************************************************");

        // Adding Theaters to BookMyShow App
        ArrayList<Theatre> theaterArrayList = new ArrayList<>();
        theaterArrayList.add(pvr);
        theaterArrayList.add(inox);
        BookMyShow bookMyShow = new BookMyShow(theaterArrayList);


        /*System.out.println("Showing List of Movies: ");
        System.out.println(ironMan.getName() + "\n" + avengers.getName() + "\n" + anabelle.getName()
                + "\n" + golmaal.getName());
        System.out.println();*/

        // Searching Book My Show for all the shows of movie Iron Man
        //System.out.println("Searching for Movie: ");


        ArrayList<Show> searchedShow = BookMyShow.searchShows("Avengers");
        System.out.println();
        System.out.println("Searching for Avengers in nearby Theatres: ");

        System.out.println("--------------------------------------------------------------------------------------------------");

        for (int i = 0; i < searchedShow.size(); i++) {
            System.out.println(searchedShow.get(i));
        }
        System.out.println("--------------------------------------------------------------------------------------------------");

        // Returns two shows of Avengers
        // Now suppose user1 and user2 both want to book 10 tickets each for first show
        // Then Book My show will create two Ticket Booking  for their requests

        for (int i = 0; i < searchedShow.size(); i++) {
            Show bookingShow1;
            System.out.println(bookingShow1 = searchedShow.get(i));

            // Ticket Booking Thread for the request made by Ben for 10 Seats

            TicketBooking t1 = new TicketBooking(bookingShow1, registeredUser1, 10, PaymentStatus.PAID, PaymentMethod.CARD);

            // Ticket Booking Thread for the request made by Jim for 10 Seats
            TicketBooking t2 = new TicketBooking(bookingShow1, registeredUser2, 10, PaymentStatus.PAID, PaymentMethod.CASH);

            // No of Seats exceeded
            // TicketBooking t1 = new TicketBooking(bookingShow1, registeredUser1, 70, PaymentStatus.PAID, PaymentMethod.CARD);

            // Start both the Ticket Booking for execution
            t1.start();
            t2.start();

            // Waiting till both the thread completes the execution
            try {
                t1.join();
                t2.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // After execution t1 will carry user 1 ticket and t2 will carry user 2 ticket
            Ticket registeredUser1_ticket = t1.getTicket();
            Ticket registeredUser2_ticket = t2.getTicket();

            // Printing their tickets
            System.out.println(registeredUser1_ticket);
            System.out.println(registeredUser2_ticket);


            // User 1 wants another 15 seats and user 2 wants another 10 seats to be booked
            // Ticket Booking for the request made by user 1 for another 15 Seats
            TicketBooking t3 = new TicketBooking(bookingShow1, registeredUser1, 15, PaymentStatus.PAID, PaymentMethod.CASH);

            // Ticket Booking for the request made by user 2 for another 10 Seats
            TicketBooking t4 = new TicketBooking(bookingShow1, registeredUser2, 10, PaymentStatus.PAID, PaymentMethod.CARD);
            // Start both the Ticket Booking Threads for execution
            t3.start();
            t4.start();

            // Tickets are not booked for user 2 if Payment Status is UNPAID
            // TicketBooking t5 = new TicketBooking(bookingShow1, registeredUser2, 10, PaymentStatus.UNPAID,null);
            // t5.stop();

            try {
                t4.join();
                t3.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // After execution t3 will carry user 1 ticket and t4 will carry user 2 ticket
            Ticket regUser1NewTicket = t3.getTicket();
            Ticket regUser2NewTicket = t4.getTicket();

            System.out.println(regUser1NewTicket);
            System.out.println(regUser2NewTicket);

        }
    }
}

