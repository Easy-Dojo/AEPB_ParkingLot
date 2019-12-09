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

    @Test
    void test_should_park_successfully_when_park_given_parking_boy_managed_parking_lot_has_space() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingAble parkingBoy = new SuperParkingBoy(parkingLot);

        ParkingManager parkingManager = new ParkingManager(parkingBoy);
        Car car = new Car();
        Ticket ticket = parkingManager.park(car);
        assertSame(car, parkingLot.pick(ticket));
    }

    @Test
    void test_should_park_successfully_when_park_given_second_parking_boy_managed_parking_lot_has_space_and_first_is_full() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(1);

        ParkingAble superParkingBoy = new SuperParkingBoy(parkingLot);
        ParkingAble smartParkingBoy = new SmartParkingBoy(parkingLot2);

        ParkingManager parkingManager = new ParkingManager(superParkingBoy, smartParkingBoy);
        Car car = new Car();
        Ticket ticket = parkingManager.park(car);
        assertSame(car, parkingLot2.pick(ticket));
    }

    @Test
    void test_should_throw_exception_when_park_given_both_parking_lots_are_full_managed_by_parking_boy() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());

        ParkingAble superParkingBoy = new SuperParkingBoy(parkingLot);
        ParkingAble smartParkingBoy = new SmartParkingBoy(parkingLot2);

        ParkingManager parkingManager = new ParkingManager(superParkingBoy, smartParkingBoy);

        assertThrows(ParkingLotFullException.class, () -> parkingManager.park(new Car()));
    }

    @Test
    void test_should_pick_my_car_when_pick_given_a_parking_lot_contains_my_car() throws ParkingLotFullException, TicketInvalidException {
        ParkingAble parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        Car myCar = new Car();
        Ticket ticket = parkingLot.park(myCar);

        ParkingManager parkingManager = new ParkingManager(parkingLot);

        assertSame(myCar, parkingManager.pick(ticket));
    }

    @Test
    void test_should_pick_my_car_when_pick_given_multiple_parking_lots_contains_my_car() throws ParkingLotFullException, TicketInvalidException {
        ParkingAble parkingLot = new ParkingLot(2);
        ParkingAble parkingLot2 = new ParkingLot(2);
        parkingLot.park(new Car());
        parkingLot2.park(new Car());

        Car myCar = new Car();

        Ticket ticket = parkingLot2.park(myCar);

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingLot2);

        assertSame(myCar, parkingManager.pick(ticket));
    }
}
