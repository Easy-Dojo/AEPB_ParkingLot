package dojo;

import dojo.exception.ParkingLotFullException;

public interface ParkingAble {
    Ticket park(Car car) throws ParkingLotFullException;

    boolean isFull();
}
