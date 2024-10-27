package lld.parking_lot.entities.vehicle;

public class Truck extends Vehicle {

    public static final VehicleType type = VehicleType.TRUCK;

    public Truck(String regNum) {
        super(type, regNum);
    }
}
