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
            System.out.println(STR."\{Thread.currentThread().getName()} locked resource");

            if (isAvailable) {

                System.out.println(STR."\{Thread.currentThread().getName()} resource is available, waiting for it to get consumed");
                if (resourceNotConsumed.await(3, TimeUnit.SECONDS)) {
                    System.out.println(STR."\{Thread.currentThread().getName()} notified/intrupted, resuming task");

                    if (isAvailable) {
                        System.out.println(STR."resource not consumend after waiting, \{Thread.currentThread().getName()} returing without processing");
                        return;
                    }
                }
            }

            Thread.sleep(1000);
            isAvailable = true; //making the resource available
            resourceNotAvailable.signalAll(); // signal all the thread waiting for lock because of resourceNotAvailable condition

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} is done, releasing monitor lock and notifying all waiting threads");
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {

        lock.lock();

        try {
            System.out.println(STR."\{Thread.currentThread().getName()} locked \{this.getClass().getName()}");

            if (!isAvailable) {

                System.out.println(STR."resource is not available, \{Thread.currentThread().getName()} waiting");

                if (resourceNotAvailable.await(3, TimeUnit.SECONDS)) {
                    System.out.println(STR."\{Thread.currentThread().getName()} notified/intrupted, resuming task");

                    if (!isAvailable) {
                        System.out.println(STR."resource not available after waiting, \{Thread.currentThread().getName()} returing without processing");
                        return;
                    }
                }
            }

            Thread.sleep(1000);
            isAvailable = false; // consuming the available resource
            resourceNotConsumed.signalAll();

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} is done, unlocking");
            lock.unlock();
        }
    }
}
