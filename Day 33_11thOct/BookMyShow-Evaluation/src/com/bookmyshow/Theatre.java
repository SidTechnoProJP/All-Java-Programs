package com.bookmyshow;

import java.util.ArrayList;

public class Theatre {
    private static int idCounter = 0;
    private int theatreId;
    private String theatreName;
    private String theatreLocation;
    private int theatreCapacity;
    private ArrayList<Show> shows;

    public Theatre(String theatreName, String theatreLocation, int theatreCapacity) {
        idCounter += 1;
        this.theatreId = idCounter;
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
        this.theatreCapacity = theatreCapacity;
        this.shows = new ArrayList<>();
    }

    public void updateShow(Show oldShow, Show newShow) {

    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public String getName() {
        return theatreName;
    }

    public int getCapacity() {
        return theatreCapacity;
    }

}
