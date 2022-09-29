package com.mydemo;

public class Android implements Phone {

    @Override
    public String getOS() {
        return "Android 13";
    }

    @Override
    public String getBrand() {
        return "Google";
    }
}
