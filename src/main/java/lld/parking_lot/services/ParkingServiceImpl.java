package lld.parking_lot.services;

import lld.parking_lot.entities.Ticket;
import lld.parking_lot.entities.vehicle.Vehicle;

// singleton class
public class ParkingServiceImpl implements ParkingService {

    private static ParkingService instance;

    private ParkingServiceImpl() {}

    public synchronized static ParkingService getParkingService() {
        if(instance == null) {
            instance = new ParkingServiceImpl();
        }

        return instance;
    }

    @Override
    public Ticket geneateTicket(Vehicle vehicle) {
        return null;
    }

    @Override
    public Ticket processExit(Vehicle vehicle) {
        return null;
    }

    @Override
    public Ticket validateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket processPayment(Ticket ticket) {
        return null;
    }
}
