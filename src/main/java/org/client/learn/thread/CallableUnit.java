package org.client.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableUnit {
    public static void main(String[] args) throws Exception {
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(task);
        Future<Integer> future2 = executor.submit(task);
        System.out.println("结果: " + future.get()); // 阻塞直到完成
        System.out.println("结果: " + future2.get()); // 阻塞直到完成
        executor.shutdown();
    }
}

//NOTE    是什么：和 Runnable 类似，但有返回值、能抛异常。
//        怎么用：实现 Callable<V>，然后交给 ExecutorService.submit()。
//        关键点：
//            返回值类型通过泛型指定。
//            必须配合 Future 才能拿到结果。
//        适用场景：需要返回结果或捕获异常的异步任务。