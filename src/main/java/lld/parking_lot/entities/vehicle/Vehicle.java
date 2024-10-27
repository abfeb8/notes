package lld.parking_lot.entities.vehicle;

public abstract class Vehicle {

    private final VehicleType type;
    private final String regNum;

    public Vehicle(VehicleType type, String regNum) {
        this.regNum = regNum;
        this.type = type;
    }

    public String getRegNum() {
        return regNum;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "type=" + type +
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
