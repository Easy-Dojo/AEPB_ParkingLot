package dojo;

import dojo.exception.ParkingLotFullException;

import java.util.Comparator;

public class SmartParkingBoy extends GraduateParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        return parkingLotList.stream()
                .max(Comparator.comparingInt(ParkingLot::availableSpace))
                .filter(parkingLot -> !parkingLot.isFull())
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }
}
