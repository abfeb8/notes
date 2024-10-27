package multithreading.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class ResourceWithReadWriteLockTest {

    public static void main(String[] args) {
        testReadWriteLock();
    }

    private static void testReadWriteLock() {
        var readWriteLock = new ReentrantReadWriteLock();

        var resource = new ResourceWithReadWriteLock(readWriteLock);

        for (int i = 1; i <= 3; i++) {
            var producerThread = new Thread(new Pub(resource), String.format("PRODUCER_THREAD_%s", i));
            var consumerThread = new Thread(new Sub(resource), String.format("CONSUMER_THREAD_%s", i));

            producerThread.start();
            consumerThread.start();
        }
    }

}

class Pub implements Runnable {

    private final ResourceWithReadWriteLock resource;

    Pub(ResourceWithReadWriteLock resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("Pub thread invoked");
        resource.produce();
    }
}

class Sub implements Runnable {

    private final ResourceWithReadWriteLock resource;

    Sub(ResourceWithReadWriteLock resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("Sub thread invoked");
        resource.consume();
    }
}