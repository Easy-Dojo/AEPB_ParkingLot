package dojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
When 停1辆车
Then 返回一张停车票
Given 有一个停车场，该停车场有1个空车位

Given 有两个停车场，第一个有足够多空车位
When 连续多辆车
Then 两辆车都停在第一个停车场

Given 有两个停车场，每个都只有适量的空车位位
When 连续多辆车
Then 多辆车按顺序分别停在两个停车场上

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
    void test_should_return_a_ticket_when_park_a_car_given_a_parking_lot_with_a_space() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);

        Ticket ticket = graduateParkingBoy.park(new Car());

        Assertions.assertNotNull(ticket);
    }
}
