package org.client.learn.thread.pool;

public class TestMDC {

    public static void main(String[] args) {
        Runnable task = () -> {
            MiniMDC.put("traceId", Thread.currentThread().getName());
            System.out.println(MiniMDC.get("traceId"));
        };
        new Thread(task, "A").start();
        new Thread(task, "B").start();
    }
}
