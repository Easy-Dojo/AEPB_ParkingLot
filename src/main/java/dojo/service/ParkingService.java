package dojo.service;

import dojo.Car;
import dojo.ParkingAble;
import dojo.Ticket;
import dojo.exception.TicketInvalidException;

import java.util.List;

public class ParkingService {

    private ParkingService() { throw new IllegalStateException("Utility class");}

    public static Car pick(List<? extends ParkingAble> parkingAbleList, Ticket ticket) throws TicketInvalidException {
        return parkingAbleList.stream()
                .filter(parkingAble -> parkingAble.contains(ticket))
                .findFirst()
                .orElseThrow(TicketInvalidException::new).pick(ticket);
    }
}
