package dojo;

import dojo.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingManager extends GraduateParkingBoy {
    private List<ParkingAble> parkingAbleList = new ArrayList<>();

    public ParkingManager(ParkingAble... parkingAbles) {
        this.parkingAbleList.addAll(Arrays.asList(parkingAbles));
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        for (ParkingAble parkingAble : parkingAbleList) {
            if (!parkingAble.isFull()) {
                return parkingAble.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

}
