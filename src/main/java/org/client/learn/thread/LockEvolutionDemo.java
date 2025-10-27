package org.client.learn.thread;

import org.openjdk.jol.info.ClassLayout;

/**
 * 锁状态升级例子
 * jvm 参数 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 */
public class LockEvolutionDemo {

    static Object lock = new Object();

    public static void main(String[] args) throws Exception {

        // 让 JVM 启动的偏向锁延迟（默认 4 秒）生效
        Thread.sleep(5000);

        System.out.println("1️⃣ 初始状态（无锁）：");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println("2️⃣ 偏向锁状态（第一次加锁）：");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        // 第二个线程参与，触发轻量级锁
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("3️⃣ 第二个线程竞争（轻量级锁或升级中）");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });

        t1.start();
        t1.join();

        // 多线程激烈竞争，升级重量级锁
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("4️⃣ 激烈竞争中（可能是重量级锁）");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("4️⃣ 激烈竞争中（可能是重量级锁）");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });

        t2.start();
        t3.start();
        t2.join();
        t3.join();

        System.out.println("5️⃣ 结束后状态：");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}


//1️⃣ 初始状态（无锁）：
//...  mark word: 001 (no lock)
//
//2️⃣ 偏向锁状态：
//...  mark word: 101 (biased lock, thread ID recorded)
//
//3️⃣ 第二个线程竞争：
//...  mark word: 00 (lightweight lock, using CAS)
//
//4️⃣ 激烈竞争中：
//...  mark word: 10 (heavyweight monitor lock)
//
//5️⃣ 结束后状态：
//...  10 (still heavyweight, 不会降级)
