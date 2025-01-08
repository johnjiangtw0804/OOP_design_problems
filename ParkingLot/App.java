package com.johnjiangtw0804.repo.ParkingLot;
/*
 * Design a parking lot system
 * Handle different types of vehicles of their respective parking space needs
 * Create vehicle types easily
 *
 * Couple of things to notice here:
 * 1. Parking lot: Single instance
 * 2. Cars
 */
public class App {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance(10); // Parking Lot with 10 spots

        Vehicle car = new Car("ABC123", "Toyota");
        Vehicle motorcycle = new Motorcycle("XYZ789","Kawasakii");
        Vehicle truck = new Bus("LMN456", "Ford");

        lot.parkVehicle(car);         // Car parks
        lot.parkVehicle(motorcycle);  // Motorcycle parks
        lot.parkVehicle(truck);       // Truck parks
    }
}
