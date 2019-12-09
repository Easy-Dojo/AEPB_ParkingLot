package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;

public interface ParkingAble {
    Ticket park(Car car) throws ParkingLotFullException;

    boolean isFull();

    boolean contains(Ticket ticket);

    Car pick(Ticket ticket) throws TicketInvalidException;
}
