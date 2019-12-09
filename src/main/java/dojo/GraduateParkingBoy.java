package dojo;

import dojo.exception.ParkingLotFullException;

public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new).park(car);
    }
}
