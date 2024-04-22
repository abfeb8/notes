package multithreading.syncronized;

public class SharedResource {

    boolean isAvailable = false;

    /**
     * Producer will make the resource available,
     * if the resource is available then the producer will wait for it to get consumed
     */
    public synchronized void produce() throws InterruptedException {
        System.out.println(STR."\{Thread.currentThread().getName()} acquired monitor lock");

        if (isAvailable) {
            System.out.println(STR."resource is available, \{Thread.currentThread().getName()} waiting for it to get consumed");
            wait(3000);
            System.out.println(STR."\{Thread.currentThread().getName()} notified/intrupted, resuming task");
            if (isAvailable) {
                System.out.println(STR."resource not consumend after waiting, \{Thread.currentThread().getName()} returing without processing");
                notifyAll();
                return;
            }
        }

        isAvailable = true; //making the resource available
        System.out.println(STR."\{Thread.currentThread().getName()} is done, releasing monitor lock and notifying all waiting threads");
        notifyAll(); // will notify all threads waiting for monitor lock
    }

    public synchronized void consume() throws InterruptedException {
        System.out.println(STR."\{Thread.currentThread().getName()} acquired monitor lock");

        if (!isAvailable) {
            System.out.println(STR."resource is not available, \{Thread.currentThread().getName()} waiting");
            wait(3000);
            System.out.println(STR."\{Thread.currentThread().getName()} notified/intrupted, resuming task");
            if (!isAvailable) {
                System.out.println(STR."resource not available after waiting, \{Thread.currentThread().getName()} returing without processing");
                notifyAll();
                return;
            }
        }

        isAvailable = false; // consuming the available resource
        System.out.println(STR."\{Thread.currentThread().getName()} is done, releasing monitor lock and notifying all waiting threads");
        notifyAll(); // will notify all threads waiting for monitor lock
    }
}
