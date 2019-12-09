package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy implements ParkingAble {
    protected List<ParkingLot> parkingLotList = new ArrayList<>();

    public GraduateParkingBoy(ParkingLot ...parkingLots) {
        this.parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        for (ParkingLot lot : parkingLotList) {
            if (!lot.isParkingLotFull()) {
                return lot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    @Override
    public boolean isFull() {
        return parkingLotList.stream().allMatch(ParkingAble::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLotList.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
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
