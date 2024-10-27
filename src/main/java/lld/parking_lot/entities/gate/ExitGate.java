package lld.parking_lot.entities.gate;

import lld.parking_lot.entities.Ticket;
import lld.parking_lot.entities.vehicle.Vehicle;
import lld.parking_lot.services.ParkingService;
import lld.parking_lot.services.ParkingServiceImpl;

public class ExitGate extends Gate {

    public static final GateType type = GateType.EXIT;
    private final ParkingService parkingService;

    public ExitGate() {
        super(type);
        this.parkingService = ParkingServiceImpl.getParkingService();
    }

    public Ticket requestExit(Vehicle vehicle) {
        var ticket = parkingService.processExit(vehicle);
        System.out.println(ticket);

        return ticket;
    }

}
