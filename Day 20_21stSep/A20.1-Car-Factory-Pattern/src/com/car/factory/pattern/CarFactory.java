package com.car.factory.pattern;

public class CarFactory {
    public static Car buildCar(CarType model) {
        Car car = null;
        switch (model) {
            case SMALL:
                car = new SmallCar();
                break;
            case SEDAN:
                car = new SedanCar();
                break;
            case LUXURY:
                car = new LuxuryCar();
                break;
            default:
                System.out.println("Error");
                break;
        }
        return car;
    }

    public static void main(String[] args) {
        System.out.println(CarFactory.buildCar(CarType.SMALL)+"\n");
        System.out.println(CarFactory.buildCar(CarType.SEDAN)+"\n");
        System.out.println(CarFactory.buildCar(CarType.LUXURY));
    }
}
