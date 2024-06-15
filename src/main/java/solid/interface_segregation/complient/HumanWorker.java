package solid.interface_segregation.complient;

/**
 * HumanWorker implements both interfaces Human and Worker
 */
public class HumanWorker implements Human, Worker{

    @Override
    public void eat() {
        // eating
    }

    @Override
    public void work() {
        // working
    }
}
