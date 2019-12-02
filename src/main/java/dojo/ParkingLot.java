package dojo;

import dojo.exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private int space;
    private HashMap<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int space) {
        this.space = space;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        Ticket ticket = new Ticket();
        if (parkedCars.size() >= space) {
            throw new ParkingLotFullException();
        }
        parkedCars.put(ticket, car);

        return ticket;
    }

    public Car pick(Ticket ticket) {
        return parkedCars.get(ticket);
    }
}
