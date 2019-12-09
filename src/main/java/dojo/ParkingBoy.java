package dojo;

import dojo.exception.TicketInvalidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParkingBoy implements ParkingAble {
    protected List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot ...parkingLots) {
        this.parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    @Override
    public boolean isFull() {
        return parkingLotList.stream().allMatch(ParkingAble::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLotList.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }

    @Override
    public Car pick(Ticket ticket) throws TicketInvalidException {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .findFirst()
                .orElseThrow(TicketInvalidException::new).pick(ticket);
    }
}
