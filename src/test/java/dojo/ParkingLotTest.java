package dojo;


import dojo.exception.ParkingLotFullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    public void test_should_return_a_valid_ticket_when_park_a_car_given_parking_lot_with_space() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        Assertions.assertNotNull(ticket);
    }

    @Test
    void test_should_return_multiple_valid_ticket_when_park_multiple_car_given_parking_lot_with_enough_space() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(3);

        Ticket ticket = parkingLot.park(new Car());
        Ticket ticket1 = parkingLot.park(new Car());

        Assertions.assertNotNull(ticket);
        Assertions.assertNotNull(ticket1);

        Assertions.assertNotSame(ticket,ticket1);
    }

    @Test
    void test_should_fail_when_park_car_given_parking_lot_full() {
        ParkingLot parkingLot = new ParkingLot(0);

        Assertions.assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()));
    }
}
