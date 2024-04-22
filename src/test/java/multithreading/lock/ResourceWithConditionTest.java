package multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceWithConditionTest {

    public static void main(String[] args) throws InterruptedException {
        producerAndConsumerUsesConditionalResource();
    }

    private static void producerAndConsumerUsesConditionalResource() {
        Lock lock = new ReentrantLock();
        // single resource instance shared across producers and consumers
        var resource = new ResourceWithCondition(lock);

        /*
         * runnable can be implemented by creating a class or by using lambda function
         * Producer is using class
         * Consumer is using lambda function
         */
        var producer = new Producer(resource);
        var consumer = new Consumer(resource);

        var consumerThread = new Thread(consumer, "CONSUMER_THREAD_1");
        var producerThread = new Thread(producer, "PRODUCER_THREAD_1");

        consumerThread.start();
        producerThread.start();

    }
}

class Producer implements Runnable {

    private final ResourceWithCondition resource;

    public Producer(ResourceWithCondition resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println(STR."\{Thread.currentThread().getName()} invoked");
        try {
            resource.produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable {

    private final ResourceWithCondition resource;

    public Consumer(ResourceWithCondition resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println(STR."\{Thread.currentThread().getName()} invoked");
        try {
            resource.consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}