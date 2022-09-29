package com.mydemo;

public class IOS implements Phone {

    @Override
    public String getOS() {
        return "iOS 16";
    }

    @Override
    public String getBrand() {
        return "Apple";
    }
}
