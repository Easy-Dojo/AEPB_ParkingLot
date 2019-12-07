package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given 有两个停车场，每个都只有适量的空车位位
When 连续多辆车
Then 多辆车按顺序分别停在两个停车场上，先停满第一个再停满第二个

Given 有两个停车场，第一个满了，第二个有足够的空车位
When 连续多辆车
Then 多辆车都停在第二个停车场上

Given 有两个停车场，两个停车场车位都满了
When 停车
Then 停车失败

Given 停车场只停了我的车
When 用我的停车票取车
Then 取走我的车

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
    void test_should_park_into_first_when_park_multiple_cars_given_first_with_enough_space_two_is_full() throws TicketInvalidException, ParkingLotFullException {
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
}
