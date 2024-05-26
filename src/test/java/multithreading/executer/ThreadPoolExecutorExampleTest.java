package multithreading.executer;

import java.util.concurrent.ThreadPoolExecutor;

import static multithreading.executer.ThreadPoolExecutorExample.getThreadPoolExecutor;

class ThreadPoolExecutorExampleTest {

    public static void main(String[] args) {

        try (ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor()) {
            // submitting task
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.submit(task());
            }
        }

        System.out.println("execution complete");
    }

    private static Runnable task() {
        return () -> {
            try {
                Thread.sleep(1000);
                System.out.println(STR."Thread name: \{Thread.currentThread().getName()}");
            } catch (Exception e) {
                // handle exception
            }
        };
    }
}