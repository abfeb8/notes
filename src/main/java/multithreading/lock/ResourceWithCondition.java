package multithreading.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ResourceWithCondition {
    boolean isAvailable = false;

    Lock lock;
    Condition resourceNotConsumed;
    Condition resourceNotAvailable;

    public ResourceWithCondition(Lock lock) {
        this.lock = lock;
        resourceNotConsumed = this.lock.newCondition();
        resourceNotAvailable = this.lock.newCondition();
    }


    /**
     * Producer will make the resource available,
     * if the resource is available then the producer will wait for it to get consumed
     */
    public void produce() throws InterruptedException {

        lock.lock();

        try {
            System.out.printf("%s locked resource%n", Thread.currentThread().getName());

            if (isAvailable) {

                System.out.printf("%s resource is available, waiting for it to get consumed%n", Thread.currentThread().getName());
                if (resourceNotConsumed.await(3, TimeUnit.SECONDS)) {
                    System.out.printf("%s notified/interrupted, resuming task%n", Thread.currentThread().getName());

                    if (isAvailable) {
                        System.out.printf("resource not consumed after waiting, %s returning without processing%n", Thread.currentThread().getName());
                        return;
                    }
                }
            }

            Thread.sleep(1000);
            isAvailable = true; //making the resource available
            resourceNotAvailable.signalAll(); // signal all the thread waiting for lock because of resourceNotAvailable condition

        } catch (Exception _) {

        } finally {
            System.out.printf("%s is done, releasing monitor lock and notifying all waiting threads%n", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {

        lock.lock();

        try {
            System.out.printf("%s locked %s%n", Thread.currentThread().getName(), this.getClass().getName());

            if (!isAvailable) {

                System.out.printf("resource is not available, %s waiting%n", Thread.currentThread().getName());

                if (resourceNotAvailable.await(3, TimeUnit.SECONDS)) {
                    System.out.printf("%s notified/interrupted, resuming task%n", Thread.currentThread().getName());

                    if (!isAvailable) {
                        System.out.printf("resource not available after waiting, %s returning without processing%n", Thread.currentThread().getName());
                        return;
                    }
                }
            }

            Thread.sleep(1000);
            isAvailable = false; // consuming the available resource
            resourceNotConsumed.signalAll();

        } catch (Exception _) {

        } finally {
            System.out.printf("%s is done, unlocking%n", Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
