package solid.interface_segregation.incomplient;

/**
 * RobotWorker is implementing both work() and eat() method.
 * where the eat() method is not required
 */
public class RobotWorker implements Worker {

    @Override
    public void work() {
        // working
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException();
    }
}
