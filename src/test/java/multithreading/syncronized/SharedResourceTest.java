package multithreading.syncronized;

class SharedResourceTest {

    public static void main(String[] args) {

        producerAndConsumerUsesSameResourceInstance();

        /*
         * since "synchronized" depends on the monitor lock of an object instance,
         * if threads are operating on different obj then they will be unaffected by synchronized method/block.
         */
//        producerAndConsumerUsesDifferentResourceInstance();

    }

    private static void producerAndConsumerUsesSameResourceInstance() {
        // single resource instance shared across producers and consumers
        var sharedResource = new SharedResource();

        /*
         * runnable can be implemented by creating a class or by using lambda function
         * Producer is using class
         * Consumer is using lambda function
         */
        var producer = new Producer(sharedResource);

        Runnable consumer = () -> {
            System.out.println(STR."\{Thread.currentThread().getName()} invoked");
            try {
                sharedResource.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var consumer_thread = new Thread(consumer, "CONSUMER_THREAD_1");
        var producer_thread = new Thread(producer, "PRODUCER_THREAD_1");

        consumer_thread.start();
        producer_thread.start();
    }

    private static void producerAndConsumerUsesDifferentResourceInstance() {
        // single resource instance shared across producers and consumers
        var resource1 = new SharedResource();
        var resource2 = new SharedResource();

        /*
         * runnable can be implemented by creating a class or by using lambda function
         * Producer is using class
         * Consumer is using lambda function
         */
        var producer = new Producer(resource1);

        Runnable consumer = () -> {
            System.out.println(STR."\{Thread.currentThread().getName()} invoked");
            try {
                resource2.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var consumer_thread = new Thread(consumer, "CONSUMER_THREAD_1");
        var producer_thread = new Thread(producer, "PRODUCER_THREAD_1");

        consumer_thread.start();
        producer_thread.start();
    }
}

class Producer implements Runnable {

    private final SharedResource sharedResource;

    public Producer(SharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println(STR."\{Thread.currentThread().getName()} invoked");
        try {
            sharedResource.produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}