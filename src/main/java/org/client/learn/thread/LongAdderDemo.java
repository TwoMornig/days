package org.client.learn.thread;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder adder = new LongAdder();
        int threadCount = 100;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    adder.increment(); // 本质是 add(1)
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("最终结果：" + adder.sum());
    }
}
