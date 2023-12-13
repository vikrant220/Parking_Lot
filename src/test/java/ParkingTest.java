import org.example.*;
import org.example.CarIsAlreadyParkedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingTest {
    @Test
    void parkingShouldAllowToParkWhenSpaceIsAvailable() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        assertTrue(canPark);
    }

    @Test
    void parkingShouldNotAllowToParkWhenCapacityIsZero() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(0);

        assertThrows(ParkingIsFullException.class, () ->  parking.park(car1));
    }


    @Test
    void parkingShouldNotAllowToParkWhenParkingCapacityBecomesZero() throws ParkingIsFullException,CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(2);

        boolean canPark1 = parking.park(car1);
        boolean canPark2 = parking.park(car2);

        assertThrows(ParkingIsFullException.class, () -> parking.park(car3));
    }

    @Test
    void parkingShouldNotAllowToParkWhencarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Car car1 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        assertTrue(canPark);
    }

    @Test
    void parkingShouldAllowToUnParkMyCarWhenCarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        boolean unPark = parking.unPark(car1);

        assertTrue(unPark);
    }

    @Test
    void parkingShouldReturnTrueOnCheckIfMyParkCarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Observer owner = Mockito.mock(Observer.class);

        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        boolean isParked = parking.isParked(car1);

        assertTrue(isParked);
    }

    @Test
    void mockObserverNotifyMethodWhenParkingIsFull() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Observer owner = Mockito.mock(Observer.class);
        Car car1 = new Car();
        Parking parking = new Parking(1);
        parking.addObserver(owner);
        parking.park(car1);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenParkingIsFull();
    }

    @Test
    void mockObserverNotifyMethodWhenParkingIsAvailable() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Observer owner = Mockito.mock(Observer.class);
        Car car1 = new Car();
        Parking parking = new Parking(1);
        parking.addObserver(owner);
        parking.park(car1);
        parking.unPark(car1);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenParkingIsAvailable();
    }

    @Test
    void parkingShouldNotifyObserverAndTrafficCopWhenParkingIsFull() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Observer owner = Mockito.mock(Observer.class);
        Observer trafficCop = Mockito.mock(Observer.class);
        Car car1 = new Car();
        Parking parking = new Parking(1);
        parking.addObserver(owner);
        parking.addObserver(trafficCop);
        parking.park(car1);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenParkingIsFull();
        Mockito.verify(trafficCop, Mockito.times(1)).notifyWhenParkingIsFull();
    }

    @Test
    void parkingShouldNotifyObserverAndTrafficCopWhenParkingIsAvailable() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Observer owner = Mockito.mock(Observer.class);
        Observer trafficCop = Mockito.mock(Observer.class);
        Car car1 = new Car();
        Parking parking = new Parking(1);
        parking.addObserver(owner);
        parking.addObserver(trafficCop);
        parking.park(car1);
        parking.unPark(car1);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenParkingIsFull();
        Mockito.verify(trafficCop, Mockito.times(1)).notifyWhenParkingIsFull();
    }
}
