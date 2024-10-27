package lld.parking_lot.entities.vehicle;

public class VehicleFactory {

    public static Vehicle buildVehicle(VehicleType type, String registrationNumber) {
        return switch (type) {
            case CAR -> new Car(registrationNumber);
            case BIKE -> new Bike(registrationNumber);
            case TRUCK -> new Truck(registrationNumber);
        };
    }
}
