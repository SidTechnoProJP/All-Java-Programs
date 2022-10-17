package com.bookmyshow;

public class Admin extends User {
    Theatre theatre;
    Show show;

    public Admin(String name) {
        super(name);
    }

    public void addMovie() {}

    public void addShow(Theatre theatre){
        show.setTheater(theatre);
    }

    public void updateShow(Show oldMovie, Show updatedMovie) {
        theatre.updateShow(oldMovie, updatedMovie);
    }
}
