package org.client.learn.thread.pool;

import java.util.UUID;

public class ThreadLocalDemo {

    private static final ThreadLocal<String> traceIdLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable task = () -> {
            String traceId = UUID.randomUUID().toString();
            traceIdLocal.set(traceId);
            System.out.println(Thread.currentThread().getName() + " traceId=" + traceIdLocal.get());
            traceIdLocal.remove(); // ⭐ 一定要清理
            System.out.println(Thread.currentThread().getName() + " traceId=" + traceIdLocal.get());
        };
        new Thread(task, "T-A").start();
        new Thread(task, "T-B").start();
    }
}
