package multithreading.completablefuture;

import multithreading.executer.ThreadPoolExecutorExample;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class FuturesExample {

    /**
     * {@link java.util.concurrent.Future} interface represents the result of an Async task <br>
     * It allows to:
     * <li>Check if computation is complete</li>
     * <li>Get result</li>
     * <li>Cancel running task, etc..</li>
     */
    public static void main() {
        futureWithRunnableTask();
        futureWithCallableTask();
    }

    private static void futureWithRunnableTask() {
        try (ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorExample.getThreadPoolExecutor()) {

            // submitting task will return a future
            Future<?> futureObj = threadPoolExecutor.submit(runnableTask());

            futureObj.isDone(); // return true if task is complete
            futureObj.isCancelled(); // return true if task is cancelled
            // futureObj.cancel(false); // cancels the execution of task.
        }
    }

    private static void futureWithCallableTask() {
        try (ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorExample.getThreadPoolExecutor()) {

            // submitting task will return a future
            Future<String> futureObj = threadPoolExecutor.submit(callableTask());

            String result = futureObj.get(); // blocking operation, waits for the computation to complete, and then retrieves its result.
            System.out.println(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Runnable runnableTask() {
        return () -> {
            try {
                System.out.println("runnable task starts");
                System.out.println(STR."Runnable thread name: \{Thread.currentThread().getName()}");
                Thread.sleep(1000);
            } catch (Exception e) {
                // handle exception
            }
            System.out.println("runnable task completes");
        };
    }

    /**
     * main difference between {@link Runnable} and {@link Callable} is that callable returns a value.
     *
     * @return {@link Callable} of String
     */
    private static Callable<String> callableTask() {
        return () -> {
            try {
                System.out.println("callable task starts");
                System.out.println(STR."Callable thread name: \{Thread.currentThread().getName()}");
                Thread.sleep(1000);
            } catch (Exception e) {
                // handle exception
            }
            return "callable task completes";
        };
    }
}
