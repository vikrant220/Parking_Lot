package org.example;

public class ParkingIsFullException extends Exception  {
        public ParkingIsFullException(String message) {
            super(message);
        }
}
