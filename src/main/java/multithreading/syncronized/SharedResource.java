package multithreading.syncronized;

public class SharedResource {

    boolean isAvailable = false;

    /**
     * Producer will make the resource available,
     * if the resource is available then the producer will wait for it to get consumed
     */
    public synchronized void produce() throws InterruptedException {
        System.out.printf("%s acquired monitor lock%n", Thread.currentThread().getName());

        if (isAvailable) {
            System.out.printf("resource is available, %s waiting for it to get consumed%n", Thread.currentThread().getName());
            wait(3000);
            System.out.printf("%s notified/intrupted, resuming task%n", Thread.currentThread().getName());
            if (isAvailable) {
                System.out.printf("resource not consumend after waiting, %s returing without processing%n", Thread.currentThread().getName());
                notifyAll();
                return;
            }
        }

        isAvailable = true; //making the resource available
        System.out.printf("%s is done, releasing monitor lock and notifying all waiting threads%n", Thread.currentThread().getName());
        notifyAll(); // will notify all threads waiting for monitor lock
    }

    public synchronized void consume() throws InterruptedException {
        System.out.printf("%s acquired monitor lock%n", Thread.currentThread().getName());

        if (!isAvailable) {
            System.out.printf("resource is not available, %s waiting%n", Thread.currentThread().getName());
            wait(3000);
            System.out.printf("%s notified/interrupted, resuming task%n", Thread.currentThread().getName());
            if (!isAvailable) {
                System.out.printf("resource not available after waiting, %s returning without processing%n", Thread.currentThread().getName());
                notifyAll();
                return;
            }
        }

        isAvailable = false; // consuming the available resource
        System.out.printf("%s is done, releasing monitor lock and notifying all waiting threads%n", Thread.currentThread().getName());
        notifyAll(); // will notify all threads waiting for monitor lock
    }
}
