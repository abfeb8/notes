package multithreading.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;

public class ResourceWithReadWriteLock {

    private final Queue<Integer> queue;
    private final ReadWriteLock queueLock;

    public ResourceWithReadWriteLock(ReadWriteLock queueLock) {
        this.queueLock = queueLock;
        this.queue = new LinkedList<>();
    }

    public void produce() {
        try {
            queueLock.writeLock().lock();
            System.out.println(STR."\{Thread.currentThread().getName()} acquired write lock \{LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss"))}");

            queue.add((int) (Math.random() * 10));
            Thread.sleep(3000);

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} completed, unlocking \{LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss"))}");
            queueLock.writeLock().unlock();
        }
    }

    public void consume() {
        try {
            queueLock.readLock().lock();
            System.out.println(STR."\{Thread.currentThread().getName()} acquired read lock \{LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss"))}");

            int val = queue.isEmpty() ? -1 : queue.poll();
            System.out.println(STR."\{Thread.currentThread().getName()} consumed \{val} from queue");
            Thread.sleep(1000);

        } catch (Exception _) {

        } finally {
            System.out.println(STR."\{Thread.currentThread().getName()} completed, unlocking \{LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss"))}");
            queueLock.readLock().unlock();
        }
    }
}
