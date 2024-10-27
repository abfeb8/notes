package lld.parking_lot.entities.vehicle;

public class Bike extends Vehicle {

    public static final VehicleType type = VehicleType.BIKE;

    public Bike(String regNum) {
        super(type, regNum);
    }
}
