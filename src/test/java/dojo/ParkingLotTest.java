package dojo;


import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


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

    @Test
    void test_should_pick_my_car_when_pick_given_parking_lot_with_only_my_car_parked_in() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car myCar = new Car();

        Ticket ticket = parkingLot.park(myCar);

        Car pickedCar = parkingLot.pick(ticket);

        Assertions.assertSame(myCar, pickedCar);
    }

    @Test
    void test_should_pick_my_car_when_pick_given_parking_lot_with_multiple_cars_parked_in() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(2);
        Car myCar = new Car();
        Car otherCar = new Car();

        Ticket myTicket = parkingLot.park(myCar);
        parkingLot.park(otherCar);

        Car pickedCar = parkingLot.pick(myTicket);

        Assertions.assertSame(myCar, pickedCar);
        Assertions.assertNotSame(otherCar, pickedCar);
    }

    @Test
    void test_should_fail_when_pick_with_invalid_ticket_given_parking_lot_with_cars_parked_in() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());

        Assertions.assertThrows(TicketInvalidException.class, () -> parkingLot.pick(new Ticket()));
    }

    @Test
    void test_should_fail_when_pick_twice_given_parking_lot_with_cars_parked_in() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        Ticket ticket = parkingLot.park(new Car());
        parkingLot.pick(ticket);

        assertThrows(TicketInvalidException.class, () -> parkingLot.pick(ticket));
    }
}
