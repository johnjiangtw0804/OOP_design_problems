package com.johnjiangtw0804.repo.ParkingLot;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    ArrayList<ParkingSpot> parkingSpots;

    private ParkingLot(int totalSpots) {
        parkingSpots = new ArrayList<>();
        for (int i = 0; i < totalSpots; i++) {
            int size = (int)(Math.random() * 8) + 1;
            parkingSpots.add(new ParkingSpot(i+1, size, true));
            System.out.println("ID " + (i + 1) + " has size " + size);
        }
    }

    public static ParkingLot getInstance(int totalSpots) {
        if (instance == null) {
            instance = new ParkingLot(totalSpots);
        }
        return instance;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        // this can be changed, maybe we want to assign a spot
        for (ParkingSpot spot : parkingSpots) {
            if (vehicle.parkingStrategy.canPark(spot)) {
                vehicle.park(spot);
                System.out.println(vehicle.toString() + " has parked in " + spot.getID());
                return true;
            }
        }

        System.out.println("No available spot for " + vehicle.toString());
        return false;
    }
}
