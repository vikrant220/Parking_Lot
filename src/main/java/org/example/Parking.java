package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Parking {

    private final HashSet<Car> parking = new HashSet<>();
    private int capacity;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Parking(int capacity) {
        this.capacity = capacity;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public boolean park(Car car) throws ParkingIsFullException, CarIsAlreadyParkedException {
        if (parking.size() == capacity) {
            throw new ParkingIsFullException("Parking Is Full!");
        }

        if (parking.contains(car)) {
            throw new CarIsAlreadyParkedException("Car is already parked");

        }

        parking.add(car);
        if (parking.size() == capacity) {
           observers.forEach(Observer::notifyWhenParkingIsFull);
        }

        return true;
    }

    public boolean unPark(Car car) throws CarIsNotParkedException {
        if(!isParked(car))
            throw new CarIsNotParkedException("Car Is Not Parked In The Lot");


        parking.remove(car);
        if (capacity == 1)
            observers.forEach(Observer::notifyWhenParkingIsAvailable);
        return true;

    }

    public boolean isParked(Car car) {
        return parking.contains(car);
    }
}

