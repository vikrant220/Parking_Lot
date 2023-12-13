package org.example;

import java.util.HashSet;
import java.util.Objects;

public class Parking {

    private final HashSet<Car> parking = new HashSet<>();
    private int capacity;
    public Parking(int capacity){
        this.capacity = capacity;
    }

    public boolean park(Car car) throws ParkingIsFullException, CarIsAlreadyParkedException {
        if(capacity>0) {
            if(!parking.contains(car)) {
                parking.add(car);
                capacity--;
                return true;
            }
            else{
                throw new CarIsAlreadyParkedException("Car is already parked");
            }
        }else{
            throw new ParkingIsFullException("Parking Is Full!");
        }
    }
    public boolean unPark(Car car) throws CarIsNotParkedException{
        if(isParked(car)){
            parking.remove(car);
            capacity++;
            return true;
        }
        throw new CarIsNotParkedException("Car Is Not Parked In The Lot");
    }

    public boolean isParked(Car car){
        return parking.contains(car);
    }
}

