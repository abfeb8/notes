package lld.parking_lot.services;

import lld.parking_lot.entities.Ticket;
import lld.parking_lot.entities.vehicle.Vehicle;

public interface ParkingService {

    /**
     * find empty Parking spot for the vehicle and generate a new ticket.
     * @throws RuntimeException if empty spot is not found
     */
    Ticket geneateTicket(Vehicle vehicle);

    /**
     * validate ticket -> calculate bill -> process payment
     * @throws RuntimeException if any of the step fails
     */
    Ticket processExit(Vehicle vehicle);

    Ticket validateTicket(Ticket ticket);
    Ticket processPayment(Ticket ticket);

}
