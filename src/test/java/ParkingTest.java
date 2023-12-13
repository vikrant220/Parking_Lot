import com.sun.source.tree.AssertTree;
import org.example.*;
import org.example.CarIsAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingTest {
    @Test
    void parkingShouldAllowToParkWhenSpaceIsAvailable() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        assertTrue(canPark);
    }

    @Test
    void parkingShouldNotAllowToParkWhenCapacityIsZero() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Parking parking = new Parking(0);

        assertThrows(ParkingIsFullException.class, () ->  parking.park(car1));
    }


    @Test
    void parkingShouldNotAllowToParkWhenParkingCapacityBecomesZero() throws ParkingIsFullException,CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        Parking parking = new Parking(2);

        boolean canPark1 = parking.park(car1);
        boolean canPark2 = parking.park(car2);

        assertThrows(ParkingIsFullException.class, () -> parking.park(car3));
    }

    @Test
    void parkingShouldNotAllowToParkWhencarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException{
        Car car1 = new Car();
        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        assertTrue(canPark);
    }

    @Test
    void parkingShouldAllowToUnParkMyCarWhenCarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        boolean unPark = parking.unPark(car1);

        assertTrue(unPark);
    }

    @Test
    void parkingShouldReturnTrueOnCheckIfMyParkCarIsAlreadyParked() throws ParkingIsFullException, CarIsAlreadyParkedException, CarIsNotParkedException {
        Car car1 = new Car();
        Parking parking = new Parking(2);

        boolean canPark = parking.park(car1);
        boolean isParked = parking.isParked(car1);

        assertTrue(isParked);
    }
}
