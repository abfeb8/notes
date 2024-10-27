package multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceTest {

    public static void main(String[] args) throws InterruptedException {
//        producerAndConsumerUsesSameResourceInstance();
        producerAndConsumerUsesDifferentResourceInstance();
    }

    private static void producerAndConsumerUsesSameResourceInstance() throws InterruptedException {
        Lock lock = new ReentrantLock();
        // single resource instance shared across producers and consumers
        var resource = new Resource(lock);

        var producer = new Producers(resource);
        var consumer = new Consumers(resource);

        var consumerThread = new Thread(consumer, "CONSUMER_THREAD_1");
        var producerThread = new Thread(producer, "PRODUCER_THREAD_1");

        producerThread.start();
        Thread.sleep(100); // so that producer thread stars first
        consumerThread.start();

    }

    private static void producerAndConsumerUsesDifferentResourceInstance() throws InterruptedException {
        Lock lock = new ReentrantLock();

        var resource1 = new Resource(lock);
        var resource2 = new Resource(lock);

        /*
         * producer and consumer are using different resource but the locking will still work
         */
        var producer = new Producers(resource1);
        var consumer = new Consumers(resource2);

        var consumerThread = new Thread(consumer, "CONSUMER_THREAD_1");
        var producerThread = new Thread(producer, "PRODUCER_THREAD_1");

        producerThread.start();
        Thread.sleep(100); // so that producer thread stars first
        consumerThread.start();

    }
}

class Producers implements Runnable {

    private final Resource resource;

    public Producers(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.printf("%s invoked%n", Thread.currentThread().getName());
        try {
            resource.produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Consumers implements Runnable {

    private final Resource resource;

    public Consumers(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.printf("%s invoked%n", Thread.currentThread().getName());
        try {
            resource.consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}