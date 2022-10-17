package com.bookmyshow;

public abstract class User {
    private static int idCounter = 0;
    private int userId;
    private String userName;

    public User(String name) {
        idCounter += 1;
        this.userId = idCounter;
        this.userName = name;
    }

    public String getName() {
        return userName;
    }
}
