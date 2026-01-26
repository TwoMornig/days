package org.client.learn.thread.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUnit {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicInteger.incrementAndGet();
            }
        });
        t1.start();
        for (int i = 0; i < 1000; i++) {
            atomicInteger.incrementAndGet();
        }
    }
}
