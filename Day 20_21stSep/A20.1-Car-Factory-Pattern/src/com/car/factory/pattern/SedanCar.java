package com.car.factory.pattern;

public class SedanCar extends Car {
    SedanCar() {
        super(CarType.SEDAN);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building Sedan Car");
    }
}
