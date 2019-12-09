package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingManagerTest {

    @Test
    void test_should_return_ticket_when_park_given_parking_lot_has_space_and_no_parking_boy() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingManager.park(car);

        assertSame(car, parkingLot.pick(ticket));
    }

    @Test
    void test_should_throw_exception_when_park_given_parking_lot_is_full_and_no_parking_boy() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        ParkingManager parkingManager = new ParkingManager(parkingLot);

        assertThrows(ParkingLotFullException.class, () -> parkingManager.park(new Car()));
    }

}
