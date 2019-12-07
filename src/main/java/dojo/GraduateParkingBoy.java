package dojo;

import dojo.exception.ParkingLotFullException;

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
}
