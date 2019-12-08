package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given 停车场停了多辆车，也有我的车
When 用我的停车票取车
Then 取出我的车

Given 停了我的车的停车场
When 用一张无效的票取车
Then 取车失败

Given 停了我的车的停车场
When 用同一张票取车两次
Then 第二次取车失败
 */

public class GraduateParkingBoyTest {
    @Test
    void test_should_return_a_ticket_when_park_a_car_given_a_parking_lot_with_a_space() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);

        Ticket ticket = graduateParkingBoy.park(new Car());

        Assertions.assertNotNull(ticket);
    }

    @Test
    void test_should_park_into_first_when_park_multiple_cars_given_first_with_enough_space_second_is_full() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(0);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticketForCar1 = graduateParkingBoy.park(car1);
        Ticket ticketForCar2 = graduateParkingBoy.park(car2);

        Assertions.assertNotNull(ticketForCar1);
        Assertions.assertNotNull(ticketForCar2);
        Assertions.assertEquals(parkingLot1.pick(ticketForCar1), car1);
        Assertions.assertEquals(parkingLot1.pick(ticketForCar2), car2);
    }

    @Test
    void test_should_park_both_when_park_multiple_cars_given_two_parkingLots_have_enough_space() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Ticket ticketForCar1 = graduateParkingBoy.park(car1);
        Ticket ticketForCar2 = graduateParkingBoy.park(car2);
        Ticket ticketForCar3 = graduateParkingBoy.park(car3);

        Assertions.assertNotNull(ticketForCar1);
        Assertions.assertNotNull(ticketForCar2);
        Assertions.assertNotNull(ticketForCar3);
        Assertions.assertEquals(parkingLot1.pick(ticketForCar1), car1);
        Assertions.assertEquals(parkingLot1.pick(ticketForCar2), car2);
        Assertions.assertEquals(parkingLot2.pick(ticketForCar3), car3);
    }

    @Test
    void test_should_park_into_second_when_park_multiple_cars_given_first_is_full_second_with_enough_space() throws TicketInvalidException, ParkingLotFullException {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(3);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticketForCar1 = graduateParkingBoy.park(car1);
        Ticket ticketForCar2 = graduateParkingBoy.park(car2);

        Assertions.assertNotNull(ticketForCar1);
        Assertions.assertNotNull(ticketForCar2);
        Assertions.assertEquals(parkingLot2.pick(ticketForCar1), car1);
        Assertions.assertEquals(parkingLot2.pick(ticketForCar2), car2);
    }

    @Test
    void test_should_park_fail_when_park_car_given_both_parking_lots_are_full() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Assertions.assertThrows(ParkingLotFullException.class, () -> graduateParkingBoy.park(new Car()));
    }

    @Test
    void test_should_pick_my_car_when_pick_car_given_parking_lots_only_park_my_car() throws ParkingLotFullException, TicketInvalidException {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car myCar = new Car();
        Ticket myTicket = graduateParkingBoy.park(myCar);

        Assertions.assertEquals(myCar, graduateParkingBoy.pick(myTicket));
    }
}
