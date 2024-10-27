package lld.parking_lot.entities.gate;

public abstract class Gate {

    private final GateType type;

    public Gate(GateType type) {
        this.type = type;
    }

    public Boolean open() {
        System.out.println("=== Gate Open ===");
        return true;
    }

    public Boolean close() {
        System.out.println("=== Gate Close ===");
        return true;
    }

    public GateType getType() {
        return type;
    }

}
