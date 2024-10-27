package multithreading.executer;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static ThreadPoolExecutor getThreadPoolExecutor() {

        // helps to create a customizable ThreadPool
        var threadPoolExecutor = new ThreadPoolExecutor(
                2, // number of threads initially created and keep in the pool, even if they are idle
                5, // max num of thread allowed in a pool (new thread will only be created if workQueue is full)
                20, TimeUnit.SECONDS, // thread, which are idle gets terminated after this time
                new ArrayBlockingQueue<>(5), // queue to store pending tasks
                Executors.defaultThreadFactory(), // factory for creating new thread (we can create custom factory as well. eg: new CustomThreadFactory())
                new ThreadPoolExecutor.AbortPolicy() // handler for tasks that can not be accepted by thread pool (we can create custom handler as well)
        );

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }
}

class CustomThreadFactory implements ThreadFactory {

    /**
     * Constructs a new unstarted {@code Thread} to run the given runnable.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     * @see <a href="../../lang/Thread.html#inheritance">Inheritance when
     * creating threads</a>
     */
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}

class CustomRejectionHandler implements RejectedExecutionHandler {

    /**
     * Method that may be invoked by a {@link ThreadPoolExecutor} when
     * {@link ThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link RejectedExecutionException}, which will be
     * propagated to the caller of {@code execute}.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws RejectedExecutionException if there is no remedy
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("task denied: %s%n", r.toString());
    }
}
