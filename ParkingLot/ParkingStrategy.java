package com.johnjiangtw0804.repo.ParkingLot;

class ParkingSpot {
    private int ID;
    private final int size;
    private boolean isAvailable;
    private String parkedVehiclePlate;
    public ParkingSpot(int ID, int size, boolean isAvailable) {
        this.ID = ID;
        this.size = size;
        this.isAvailable = isAvailable;
    }
    public int getID() {
        return this.ID;
    }

    public int getSize() {
        return this.size;
    }
    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void occupy(String parkedVehiclePlate) {
        if (!isAvailable) {
            return ;
        }
        isAvailable = false;
        this.parkedVehiclePlate = parkedVehiclePlate;
        System.out.println("Vehicle " + parkedVehiclePlate + " is parked in " + this.ID);
    }
    public void release() {
        isAvailable = true;
        this.parkedVehiclePlate = "";
        System.out.println("Vehicle " + parkedVehiclePlate + " has left #" + this.ID);
    }
}

// 重點在這邊停車邏輯的拆分
interface ParkingStrategy {
    boolean canPark(ParkingSpot spot);
}

class CarParkingStrategy implements ParkingStrategy {
    @Override
    public boolean canPark(ParkingSpot spot) {
        // size 這邊用 int，但是有機會應該要拆分出小型車大型車等各類型
        return spot.isAvailable() && spot.getSize() >= 5;
    }
}

class MotorcycleParkingStrategy implements ParkingStrategy {
    @Override
    public boolean canPark(ParkingSpot spot) {
        return spot.isAvailable() && spot.getSize() >= 1;
    }
}

class BusParkingStrategy implements ParkingStrategy {
    @Override
    public boolean canPark(ParkingSpot spot) {
        // here we are assuming a bus can fit up to eight people
        return spot.isAvailable() && spot.getSize() >= 8;
    }
}