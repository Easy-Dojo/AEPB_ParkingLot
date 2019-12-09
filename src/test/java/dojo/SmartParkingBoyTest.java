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


    @Test
    void test_should_park_into_first_when_park_a_car_given_two_parking_lots_which_first_with_more_spaces() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(5);
        parkingLot2.park(new Car());

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);

        assertNotNull(ticket1);
        assertSame(car1, parkingLot1.pick(ticket1));
    }

    @Test
    void test_should_park_into_second_when_park_a_car_given_two_parking_lots_which_second_with_more_spaces() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot1 = new ParkingLot(5);
        parkingLot1.park(new Car());

        ParkingLot parkingLot2 = new ParkingLot(5);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
        assertSame(car, parkingLot2.pick(ticket));
    }


    @Test
    void test_should_park_into_both_exchanged_when_park_multiple_cars_given_two_parking_lots_with_same_spaces() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(3);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        Ticket ticket3 = parkingBoy.park(car3);
        Ticket ticket4 = parkingBoy.park(car4);

        assertSame(car1, parkingLot1.pick(ticket1));
        assertSame(car2, parkingLot2.pick(ticket2));
        assertSame(car3, parkingLot1.pick(ticket3));
        assertSame(car4, parkingLot2.pick(ticket4));
    }

}
