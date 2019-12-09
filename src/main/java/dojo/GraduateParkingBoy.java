package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy {
    protected List<ParkingLot> parkingLotList = new ArrayList<>();

    public GraduateParkingBoy(ParkingLot ...parkingLots) {
        this.parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        for (ParkingLot lot : parkingLotList) {
            if (!lot.isParkingLotFull()) {
                return lot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car pick(Ticket myTicket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.contains(myTicket)) {
                return parkingLot.pick(myTicket);
            }
        }
        throw new TicketInvalidException();
    }
}
