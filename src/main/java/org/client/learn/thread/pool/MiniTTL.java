package org.client.learn.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ttl demo
 */
public class MiniTTL<T> {


    private final ThreadLocal<T> threadLocal = new ThreadLocal<>();

    public void set(T value) {
        threadLocal.set(value);
    }

    public T get() {
        return threadLocal.get();
    }


    public Runnable wrap(Runnable task) {

        // ⭐ 捕获父线程值
        T captured = threadLocal.get();

        return () -> {
            T backup = threadLocal.get();

            try {
                // ⭐ 设置到子线程
                threadLocal.set(captured);

                task.run();

            } finally {
                // ⭐ 恢复旧值（关键）
                if (backup != null) {
                    threadLocal.set(backup);
                } else {
                    threadLocal.remove();
                }
            }
        };
    }

    public static void main(String[] args) {
        MiniTTL<String> ttl = new MiniTTL<>();
        ExecutorService pool = Executors.newFixedThreadPool(1);
        ttl.set("mini-ttl-777");
        pool.submit(ttl.wrap(() -> System.out.println(ttl.get())));
        pool.shutdown();
    }
}
