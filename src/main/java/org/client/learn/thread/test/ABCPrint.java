package org.client.learn.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ABCPrint {
    private static String str = "C";
    private static Object lock = new Object();
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition reenCondition = reentrantLock.newCondition();
    private final static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> print("A", "C"));
        Thread t2 = new Thread(() -> print("B", "A"));
        Thread t3 = new Thread(() -> print("C", "B"));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void print(String newValue, String oldValue) {
        for (int i = 0; i < 10; i++) {
            reentrantLock.lock();
            try {
                while (!str.equals(oldValue)) {
                    reenCondition.await();
                }
                log.info(newValue);
                str = newValue;
                reenCondition.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
