package org.client.learn.thread.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 50;
        LongAccumulator maxAcc = new LongAccumulator(Math::max, Long.MIN_VALUE);
        Thread[] threads = new Thread[THREADS];
        CountDownLatch latch = new CountDownLatch(THREADS);

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                // 每个线程产生若干随机数并累计最大值
                for (int j = 0; j < 1000; j++) {
                    long val = ThreadLocalRandom.current().nextLong(1, 10_000);
                    maxAcc.accumulate(val); // 使用 Math.max(old, val)
                }
                latch.countDown();
            });
            threads[i].start();
        }

        latch.await();
        long max = maxAcc.get();
        System.out.println("Concurrent Max: " + max);
        // 验证：我们可以在单线程中重新计算 max 来对比（略）
    }
}
