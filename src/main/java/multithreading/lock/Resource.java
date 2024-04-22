package multithreading.lock;

import java.util.concurrent.locks.Lock;

public class Resource {
    boolean isAvailable = false;

    Lock lock;

    public Resource(Lock lock) {
        this.lock = lock;
    }


    /**
     * Producer will make the resource available
     */
    public void produce() throws InterruptedException {

        lock.lock();

        try {
            System.out.println(STR."\{Thread.currentThread().getName()} locked resource");
            Thread.sleep(1000);
            if (!isAvailable) {
                System.out.println(STR."\{Thread.currentThread().getName()} produced resource");
                isAvailable = true; //making the resource available
            } else {
                System.out.println(STR."\{Thread.currentThread().getName()} resource not consumed");
            }

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} is done, unlocking");
            lock.unlock();
        }
    }

    /**
     * consumer will consume the available resource
     */
    public void consume() throws InterruptedException {

        lock.lock();

        try {
            System.out.println(STR."\{Thread.currentThread().getName()} locked resource");
            Thread.sleep(1000);
            if (isAvailable) {
                System.out.println(STR."\{Thread.currentThread().getName()} consumed the resource");
                isAvailable = false; // consuming the available resource
            } else {
                System.out.println(STR."\{Thread.currentThread().getName()} resource not available");
            }

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} is done, unlocking");
            lock.unlock();
        }
    }
}
