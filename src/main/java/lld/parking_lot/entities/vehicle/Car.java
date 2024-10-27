package lld.parking_lot.entities.vehicle;

public class Car extends Vehicle {

    private static final VehicleType type = VehicleType.CAR;

    public Car(String regNum) {
        super(type, regNum);
    }

}
