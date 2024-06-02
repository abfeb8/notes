package multithreading.completablefuture;

import multithreading.executer.ThreadPoolExecutorExample;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureEx {

    /**
     * {@link CompletableFuture} can be considered as an advance version of {@link Future}. It helps in async programming.
     * It also supports functional chaining
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsyncExample();
        thenApplyExample();
        thenComposeExample();
        thenAcceptExample();
    }

    /**
     * {@link CompletableFuture#supplyAsync(Supplier)} Returns a new CompletableFuture that is asynchronously completed
     * by a task running in the {@link ForkJoinPool#commonPool()} with the value obtained by calling the given Supplier.
     *
     * <p> {@link CompletableFuture#supplyAsync(Supplier, Executor)} Returns a new CompletableFuture that is asynchronously
     * completed by a task running in the given executor with the value obtained by calling the given Supplier.
     */
    public static void supplyAsyncExample() throws ExecutionException, InterruptedException {
        System.out.println("====== supplyAsync Example ======");
        ThreadPoolExecutor executor = ThreadPoolExecutorExample.getThreadPoolExecutor();

        CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(
                callableTask("single_task"),
                executor
        );

        System.out.println(asyncTask.get()); // blocking operation

    }

    /**
     * apply a function to the result of previous Async computation and returns a new {@link CompletableFuture}
     *
     * <p> This method is analogous to {@link java.util.Optional#map Optional.map} and
     * {@link java.util.stream.Stream#map Stream.map}.
     *
     * <p> {@link CompletableFuture#thenApply(Function)} is synchronous execution,
     * it uses the same thread used for computing previous task.
     *
     * <p> {@link CompletableFuture#thenApplyAsync(Function)} it's asynchronous execution,
     * it uses common 'fork-join' pool for async execution.
     *
     * <p> {@link CompletableFuture#thenApplyAsync(Function, Executor)} it uses provided executor for async execution
     *
     * <li><b>If multiple {@link CompletableFuture#thenApplyAsync} are used, then ordering can't be guarantee, they will run concurrently</b></li>
     */
    public static void thenApplyExample() throws ExecutionException, InterruptedException {
        System.out.println("====== thenApply Example ======");
        ThreadPoolExecutor executor = ThreadPoolExecutorExample.getThreadPoolExecutor();

        CompletableFuture<String> asyncTask = CompletableFuture
                .supplyAsync(
                        callableTask("task_1"),
                        executor
                )
                .thenApply(s -> combinePreviousWithNext(s, "task_2"))
                .thenApplyAsync(s -> combinePreviousWithNext(s, "task_3"))
                .thenApplyAsync(s -> combinePreviousWithNext(s, "task_4"), executor);

        System.out.println(STR."result: \{asyncTask.get()}"); // blocking operation
    }

    /**
     * similar to {@link CompletableFuture#thenApplyAsync}, but takes  {@link CompletableFuture} as input
     * and returns {@link CompletionStage}
     *
     * <p>This method is analogous to {@link java.util.Optional#flatMap Optional.flatMap} and
     * {@link java.util.stream.Stream#flatMap Stream.flatMap}.
     */
    public static void thenComposeExample() throws ExecutionException, InterruptedException {
        System.out.println("====== thenCompose Example ======");
        ThreadPoolExecutor executor = ThreadPoolExecutorExample.getThreadPoolExecutor();

        CompletableFuture<String> asyncTask = CompletableFuture
                .supplyAsync(
                        callableTask("task_1"),
                        executor
                )
                .thenCompose(s -> CompletableFuture.supplyAsync(callableTask(STR."\{s} task_2")))
                .thenComposeAsync(s -> CompletableFuture.supplyAsync(callableTask(STR."\{s} task_3")))
                .thenComposeAsync(s -> CompletableFuture.supplyAsync(callableTask(STR."\{s} task_4"), executor), executor);

        System.out.println(STR."result: \{asyncTask.get()}"); // blocking operation
    }

    /**
     * terminal operation, returns {@code CompletionStage<Void>}
     */
    public static void thenAcceptExample() {
        System.out.println("====== thenAccept Example ======");

        ThreadPoolExecutor executor = ThreadPoolExecutorExample.getThreadPoolExecutor();

        CompletableFuture.supplyAsync(
                        callableTask("task_1"),
                        executor
                )
                .thenApply(s -> combinePreviousWithNext(s, "task_2"))
                .thenApply(s -> combinePreviousWithNext(s, "task_3"))
                .thenApply(s -> combinePreviousWithNext(s, "task_4"))
                .thenAccept(s -> System.out.println(STR."result: \{s}")); // non-blocking operation

        System.out.println("main thread released");
    }


    private static String combinePreviousWithNext(String previous, String next) {
        System.out.println(STR."Thread: \{Thread.currentThread().getName()}, Previous: \{previous}, Next: \{next}");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return STR."\{previous}->\{next}";
    }

    private static Supplier<String> callableTask(String taskValue) {
        return () -> {
            try {
                System.out.println(STR."callable task: \{taskValue} starts");
                System.out.println(STR."callable thread name: \{Thread.currentThread().getName()}");
                Thread.sleep(1000);
            } catch (Exception e) {
                // handle exception
            }
            System.out.println(STR."callable task: \{taskValue} completes");
            return taskValue;
        };
    }
}
