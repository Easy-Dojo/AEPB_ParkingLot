package dojo;

import dojo.exception.ParkingLotFullException;
import dojo.exception.TicketInvalidException;

import java.util.HashMap;

public class ParkingLot implements ParkingAble {
    private int space;
    private HashMap<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int space) {
        this.space = space;
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        Ticket ticket = new Ticket();
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
        parkedCars.put(ticket, car);

        return ticket;
    }

    public boolean isParkingLotFull() {
        return parkedCars.size() >= space;
    }

    public Car pick(Ticket ticket) throws TicketInvalidException {
        if (contains(ticket)) {
            return parkedCars.remove(ticket);
        }

        throw new TicketInvalidException();
    }

    public int availableSpace() {
        return space - parkedCars.size();
    }

    @Override
    public boolean isFull() {
        return parkedCars.size() >= space;
    }

    @Override
    public boolean contains(Ticket myTicket) {
        return parkedCars.containsKey(myTicket);
    }

    public double vacancyRate() {
        return (double)availableSpace() / space;
    }
}
