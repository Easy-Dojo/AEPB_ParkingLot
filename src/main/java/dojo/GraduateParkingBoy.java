package dojo;

public class GraduateParkingBoy {
    private ParkingLot parkingLot;

    public GraduateParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return new Ticket();
    }
}
