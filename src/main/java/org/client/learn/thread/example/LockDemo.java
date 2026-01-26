package org.client.learn.thread.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显示 隐式 锁的区别
 */
@Slf4j
public class LockDemo {

    private static final Object objLock = new Object(); // 用作内置锁
    private static final Lock reentrantLock = new ReentrantLock(); // 显式锁

    public static void main(String[] args) {
        // 启动两个线程，对比效果
        Thread t1 = new Thread(() -> testSynchronized("T1"));
        Thread t2 = new Thread(() -> testSynchronized("T2"));

        Thread t3 = new Thread(() -> testReentrantLock("T3"));
        Thread t4 = new Thread(() -> testReentrantLock("T4"));

        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        t3.start();
        t4.start();
    }

    // 用 synchronized 演示
    private static void testSynchronized(String name) {
        synchronized (objLock) {
            log.info(name + " 进入 synchronized 区域");
            try {
                Thread.sleep(1000); // 模拟执行
            } catch (InterruptedException ignored) {
            }
            log.info(name + " 退出 synchronized 区域");
        }
    }

    // 用 ReentrantLock 演示
    private static void testReentrantLock(String name) {
        reentrantLock.lock(); // 手动加锁
        try {
            log.info(name + " 获取了 ReentrantLock 锁");
            Thread.sleep(1000);
            log.info(name + " 释放 ReentrantLock 锁");
        } catch (InterruptedException ignored) {
        } finally {
            reentrantLock.unlock(); // 手动解锁 (必须在 finally 里写)
        }
    }
}
