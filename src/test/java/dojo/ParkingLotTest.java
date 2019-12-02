package dojo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    public void test_should_return_a_valid_ticket_when_park_a_car_given_parking_lot_with_space() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        Assertions.assertNotNull(ticket);
    }
}
