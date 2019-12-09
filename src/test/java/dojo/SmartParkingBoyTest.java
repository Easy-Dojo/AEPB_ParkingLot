package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class SmartParkingBoyTest {

    @Test
    void test_should_return_a_valid_ticket_when_park_a_car_given_a_parking_lot_with_a_space() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();

        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
        assertSame(car, parkingLot.pick(ticket));
    }

}
