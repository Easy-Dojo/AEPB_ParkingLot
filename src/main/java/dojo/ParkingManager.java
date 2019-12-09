package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;
import dojo.service.ParkingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingManager extends ParkingBoy {
    private List<ParkingAble> parkingAbleList = new ArrayList<>();

    public ParkingManager(ParkingAble... parkingAbles) {
        this.parkingAbleList.addAll(Arrays.asList(parkingAbles));
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        return parkingAbleList.stream()
                .filter(parkingAble -> !parkingAble.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new).park(car);
    }

    @Override
    public Car pick(Ticket ticket) throws TicketInvalidException {
        return ParkingService.pick(parkingAbleList, ticket);
    }
}
