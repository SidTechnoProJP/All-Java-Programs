package com.car.factory.pattern;

public class SmallCar extends Car {
    SmallCar() {
        super(CarType.SMALL);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building Small Car");
    }
}
