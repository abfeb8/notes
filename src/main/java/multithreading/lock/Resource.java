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
            System.out.printf("%s locked resource%n", Thread.currentThread().getName());
            Thread.sleep(1000);
            if (!isAvailable) {
                System.out.printf("%s produced resource%n", Thread.currentThread().getName());
                isAvailable = true; //making the resource available
            } else {
                System.out.printf("%s resource not consumed%n", Thread.currentThread().getName());
            }

        } catch (Exception _) {

        } finally {
            System.out.printf("%s is done, unlocking%n", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    /**
     * consumer will consume the available resource
     */
    public void consume() throws InterruptedException {

        lock.lock();

        try {
            System.out.printf("%s locked resource%n", Thread.currentThread().getName());
            Thread.sleep(1000);
            if (isAvailable) {
                System.out.printf("%s consumed the resource%n", Thread.currentThread().getName());
                isAvailable = false; // consuming the available resource
            } else {
                System.out.printf("%s resource not available%n", Thread.currentThread().getName());
            }

        } catch (Exception _) {

        } finally {
            System.out.printf("%s is done, unlocking%n",  Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
