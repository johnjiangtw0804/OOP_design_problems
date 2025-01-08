package com.johnjiangtw0804.repo.ParkingLot;

// 一開始想法是 Vehicle 有個 parking method 需要被 override，每一個車種類去 override
// 但是如果油車，跟電車，都是 same parking method，我們可以把 parking strategy 運用 strategy design pattern 拆出來
// 把 parking strategy 拆出來主要想法是，這裡的 parkingStrategy <-> parking spot 是有關係的，但車輛其實跟這兩件事沒有太大關係
abstract class Vehicle {
    protected String licensePlate;
    protected String model;
    protected ParkingStrategy parkingStrategy;

    public Vehicle(String licensePlate, String model, ParkingStrategy parkingStrategy) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.parkingStrategy = parkingStrategy;
    }

    public void park(ParkingSpot spot) {
        spot.occupy(this.licensePlate);
    }
}

class Car extends Vehicle{
    public Car(String licensePlate, String model) {
        super(licensePlate, model, new CarParkingStrategy());
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate, String model) {
        super(licensePlate, model, new MotorcycleParkingStrategy());
    }
}

class Bus extends Vehicle {
    public Bus(String licensePlate, String model) {
        super(licensePlate, model, new BusParkingStrategy());
    }
}