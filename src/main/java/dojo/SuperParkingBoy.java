package dojo;


import dojo.exception.ParkingLotFullException;

import java.util.Comparator;

public class SuperParkingBoy extends GraduateParkingBoy {

    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        return parkingLotList.stream()
                .max(Comparator.comparingDouble(ParkingLot::vacancyRate))
                .filter(parkingLot -> !parkingLot.isFull())
                .orElseThrow(ParkingLotFullException::new).park(car);
    }

}
