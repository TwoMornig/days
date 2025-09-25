package org.client.learn.thread;

import java.util.concurrent.*;

/**
 * 线程池demo
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1. 创建一个固定大小的线程池（比如 3 个线程）
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2. 提交 Runnable 任务（无返回值）
        executor.execute(() -> {
            System.out.println("Runnable任务执行于: " + Thread.currentThread().getName());
        });

        // 3. 提交 Callable 任务（有返回值）
        Callable<Integer> task = () -> {
            System.out.println("Callable任务执行于: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> future = executor.submit(task);

        // 4. 拿到结果（阻塞等待）
        Integer result = future.get();
        System.out.println("Callable结果: " + result);

        // 5. 关闭线程池
        executor.shutdown();
    }
}
