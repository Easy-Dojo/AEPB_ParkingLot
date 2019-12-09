package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperParkingBoyTest {

    @Test
    void test_should_return_a_valid_ticket_when_park_a_car_given_a_parking_lot_with_a_space() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot);
        Car car = new Car();

        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
        assertSame(car, parkingLot.pick(ticket));
    }

    @Test
    void test_should_park_into_first_when_park_a_car_given_two_parking_lots_which_first_with_more_vacancy_rate() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLot1.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLot2.park(new Car());
        parkingLot2.park(new Car());

        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);

        assertNotNull(ticket1);
        assertSame(car1, parkingLot1.pick(ticket1));
    }

    @Test
    void test_should_park_into_second_when_park_a_car_given_two_parking_lots_which_second_with_more_vacancy_rate() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(3);
        parkingLot1.park(new Car());
        parkingLot1.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.park(new Car());

        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);

        assertNotNull(ticket1);
        assertSame(car1, parkingLot2.pick(ticket1));
    }

    @Test
    void test_should_park_into_first_when_park_a_car_given_two_parking_lots_with_same_vacancy_rate() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(3);
        parkingLot1.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLot2.park(new Car());

        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);

        assertNotNull(ticket1);
        assertSame(car1, parkingLot1.pick(ticket1));
    }

    @Test
    void test_should_fail_when_park_a_car_given_two_parking_lots_both_full() throws ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());

        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);

        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Car()));
    }
}
