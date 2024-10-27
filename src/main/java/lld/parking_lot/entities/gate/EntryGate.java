package lld.parking_lot.entities.gate;

import lld.parking_lot.entities.Ticket;
import lld.parking_lot.entities.vehicle.Vehicle;
import lld.parking_lot.services.ParkingService;
import lld.parking_lot.services.ParkingServiceImpl;

public class EntryGate extends Gate {

    public static final GateType type = GateType.ENTRY;
    private final ParkingService parkingService;

    public EntryGate() {
        super(type);
        this.parkingService = ParkingServiceImpl.getParkingService();
    }

    public Ticket requestParking(Vehicle vehicle) {
        var ticket = parkingService.geneateTicket(vehicle);
        System.out.println(ticket);

        return ticket;
    }

}
