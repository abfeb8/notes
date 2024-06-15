package solid.dependency_inversion.incomplient;

/**
 * ‘Switch’ class directly depends on a concrete class i.e. ‘LightBulb’
 */
public class Switch {

    private LightBulb lightBulb;

    public Switch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    public void operate() {
        if (/* some condition */ true) {
            lightBulb.turnOn();
        } else {
            lightBulb.turnOff();
        }
    }

}
