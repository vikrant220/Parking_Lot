package org.example;

public class CarIsAlreadyParkedException extends Exception{
    public CarIsAlreadyParkedException(String message) {
        super(message);
    }
}
