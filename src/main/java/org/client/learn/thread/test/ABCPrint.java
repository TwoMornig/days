package org.client.learn.thread.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ABCPrint {

    private static final Object lock = new Object();
    private static volatile String state = "A";

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> print("A", "B"));
        Thread t2 = new Thread(() -> print("B", "C"));
        Thread t3 = new Thread(() -> print("C", "A"));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void print(String current, String next) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (!state.equals(current)) { // 不轮到自己就等
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info(current);
                state = next; // 修改下一个要执行的标识
                lock.notifyAll(); // 唤醒其他线程
            }
        }
    }
}
