package solid.dependency_inversion.complient;

/**
 * ‘Switch’ class depends on the interface rather than the concrete class.
 */
public class Switch {

    private SwitchableDevice device;

    public Switch(SwitchableDevice device) {
        this.device = device;
    }

    public void operate() {
        if (/* some condition */true) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }

}
