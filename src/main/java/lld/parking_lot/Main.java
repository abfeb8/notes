package lld.parking_lot;

import lld.parking_lot.entities.vehicle.VehicleFactory;
import lld.parking_lot.entities.vehicle.VehicleType;

public class Main {

    public static void main(String[] args) {
        var vehicle = VehicleFactory.buildVehicle(VehicleType.BIKE, "23BH2124C");

        System.out.println(vehicle);
    }
}
